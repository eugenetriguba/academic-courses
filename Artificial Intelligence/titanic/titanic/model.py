import matplotlib.pyplot as plt
import pandas as pd
from sklearn.base import BaseEstimator, TransformerMixin
from sklearn.impute import SimpleImputer
from sklearn.metrics import confusion_matrix, roc_auc_score, roc_curve, precision_recall_curve
from sklearn.model_selection import cross_val_predict, cross_val_score
from sklearn.pipeline import FeatureUnion, Pipeline
from sklearn.preprocessing import OneHotEncoder

from titanic.stats import ConfusionMatrixStats
from titanic.utils import save_to_plots_directory


class TrainingModel:
    def __init__(self, train_data, test_data, classifier):
        self.train_data = train_data
        self.test_data = test_data
        self.x_train, self.y_train = self.__process_through_pipeline()

        self.scores = None
        self.prediction = None
        self.cf_stats = None

        self.classifier = classifier
        self.name = type(self.classifier).__name__
        self.classifier.fit(self.x_train, self.y_train)

    def evaluate(self, output=True):
        """
        Evaluates the model by using cross validation for the score and prediction,
        creating a confusion matrix, and printing out stats for all of them.

        Cross validation scores are saved to self.scores
        Cross validation prediction is saved to self.prediction
        Confusion matrix stats are saved to self.cf_stats
        """
        self.scores = cross_val_score(
            self.classifier, self.x_train, self.y_train, cv=10
        )
        self.prediction = cross_val_predict(
            self.classifier, self.x_train, self.y_train, n_jobs=4
        )
        self.cf_stats = ConfusionMatrixStats(
            confusion_matrix(self.y_train, self.prediction)
        )

        if output:
            print(f"------------------- Evaluating {self.name} -------------------")
            print(
                f"Mean scores from cross validation using {self.name}: "
                f"{self.scores.mean():.2f}"
            )
            print(f"Confusion matrix using {self.name}: \n{self.cf_stats.matrix}")
            print(f"Precision score using {self.name}: {self.cf_stats.precision():.2f}")
            print(f"Recall score using {self.name}: {self.cf_stats.recall():.2f}")
            print(f"Accuracy score using {self.name}: {self.cf_stats.accuracy():.2f}")
            print(f"ROC AUC score using {self.name}: {roc_auc_score(self.y_train, self.prediction):.2f}")
            print()

    def plot_roc_curve(
            self, save: bool = True, show: bool = False, compare_to: "TrainingModel" = None
    ):
        """
        Plot the ROC curve of a training model. It uses the y_train data and
        the model's prediction to create an ROC graph. You may then save it
        to the plots directory and/or show it to the screen.

        :param save: Whether or not you'd like to save the curve. Defaults to True.
        :param show: Whether or not you'd like to show the curve to the screen.
                     Defaults to False.
        :param compare_to: A training model to compare roc curves to.
        """
        fpr, tpr, thresholds = roc_curve(self.y_train, self.prediction)
        plt.figure(figsize=(8, 6))
        plt.plot(fpr, tpr, linewidth=2, label=self.name)
        plt.plot([0, 1], [0, 1], "k--")
        plt.axis([0, 1, 0, 1])
        plt.xlabel("False Positive Rate (Fall-Out)", fontsize=16)
        plt.ylabel("True Positive Rate (Recall)", fontsize=16)
        plt.grid(True)

        if compare_to:
            fpr, tpr, thresholds = roc_curve(compare_to.y_train, compare_to.prediction)
            plt.plot(fpr, tpr, linewidth=2, label=compare_to.name)
            plt.legend(loc="lower right", fontsize=16)

        if show:
            plt.show()

        if save and compare_to is None:
            save_to_plots_directory(plt, f"RocCurve{self.name}")

        if save and compare_to is not None:
            save_to_plots_directory(plt, f"ComparisonRocCurve{self.name}{compare_to.name}")

    def plot_precision_and_recall(self, save: bool = True, show: bool = False):
        """
        Plot the precision and recall curve of a training model. It uses the
        y_train data and the model's prediction to create the graph. You may
        then save it to the plots directory and/or show it to the screen.

        :param save: Whether or not you'd like to save the curve. Defaults to True.
        :param show: Whether or not you'd like to show the curve to the screen.
                     Defaults to False.
        """
        precision, recall, threshold = precision_recall_curve(self.y_train, self.prediction)
        plt.figure(figsize=(8, 6))
        plt.plot(threshold, precision[:-1], "r-", label="precision", linewidth=2)
        plt.plot(threshold, recall[:-1], "b", label="recall", linewidth=2)
        plt.xlabel("threshold", fontsize=16)
        plt.legend(loc="upper right", fontsize=16)
        plt.ylim([0, 1])

        if show:
            plt.show()

        if save:
            save_to_plots_directory(plt, f"PrecRecCurve{self.name}")

    def __process_through_pipeline(self) -> tuple:
        """
        It processes the data so it is ready to use on a training model.

        :return: (x_train, y_train)
        """
        num_pipeline = Pipeline(
            [
                (
                    "select_numeric",
                    DataFrameSelector([
                        "Pclass", "Sex", "Age", "SibSp", "Parch",
                        "Fare", "Embarked", "Deck", "Title", "AgeClass"
                    ]),
                ),
                ("imputer", SimpleImputer(strategy="median")),
            ]
        )
        num_pipeline.fit_transform(self.train_data)

        x_train = num_pipeline.fit_transform(self.train_data)
        y_train = self.train_data["Survived"]

        return x_train, y_train


class DataFrameSelector(BaseEstimator, TransformerMixin):
    def __init__(self, attribute_names):
        self.attribute_names = attribute_names

    def fit(self, X, y=None):
        return self

    def transform(self, X):
        return X[self.attribute_names]


# Inspired from stackoverflow.com/questions/25239958
class MostFrequentImputer(BaseEstimator, TransformerMixin):
    def fit(self, X, y=None):
        self.most_frequent_ = pd.Series(
            [X[c].value_counts().index[0] for c in X], index=X.columns
        )
        return self

    def transform(self, X, y=None):
        return X.fillna(self.most_frequent_)
