from sklearn.ensemble import RandomForestClassifier
from sklearn.svm import SVC

from titanic.model import TrainingModel
from titanic.data import clean_data
from titanic.utils import load_titanic_data


if __name__ == "__main__":
    train_data, test_data = load_titanic_data()
    train_data = clean_data(train_data)
    test_data = clean_data(test_data)
    models = [
        SVC(C=1000, gamma=.0001, kernel="rbf"),
        RandomForestClassifier(
            n_estimators=75, random_state=42, n_jobs=8,
            max_features="auto", oob_score=True, criterion="gini",
            min_samples_leaf=1, min_samples_split=10, max_depth=25
        ),
    ]

    for model in models:
        train_model = TrainingModel(train_data, test_data, model)
        train_model.evaluate()
        train_model.plot_roc_curve()
        train_model.plot_precision_and_recall()

    # Generate a comparison roc plot
    svc = TrainingModel(train_data, test_data, models[0])
    rfc = TrainingModel(train_data, test_data, models[1])
    svc.evaluate(output=False)
    rfc.evaluate(output=False)
    svc.plot_roc_curve(compare_to=rfc)
