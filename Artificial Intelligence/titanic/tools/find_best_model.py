import os
import sys

sys.path.append(os.path.join(os.path.dirname(__file__), '../'))

from sklearn.ensemble import RandomForestClassifier
from sklearn.linear_model import Perceptron
from sklearn.linear_model import SGDClassifier
from sklearn.tree import DecisionTreeClassifier
from sklearn.neighbors import KNeighborsClassifier
from sklearn.svm import SVC
from sklearn.naive_bayes import GaussianNB

from titanic.model import TrainingModel
from titanic.data import clean_data
from titanic.utils import load_titanic_data


if __name__ == "__main__":
    train_data, test_data = load_titanic_data()
    train_data = clean_data(train_data)
    test_data = clean_data(test_data)

    models = [
        SGDClassifier(),
        RandomForestClassifier(),
        Perceptron(),
        DecisionTreeClassifier(),
        GaussianNB(),
        SVC(),
        KNeighborsClassifier(),
    ]

    for model in models:
        trained_model = TrainingModel(train_data, test_data, model)
        trained_model.evaluate()

