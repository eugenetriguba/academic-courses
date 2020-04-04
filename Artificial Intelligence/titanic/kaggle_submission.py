from sklearn.ensemble import RandomForestClassifier
from sklearn.svm import SVC

from titanic.model import TrainingModel
from titanic.data import clean_data
from titanic.utils import load_titanic_data


if __name__ == "__main__":
    train_data, test_data = load_titanic_data()
    ids = test_data[["PassengerId"]]
    train_data = clean_data(train_data)
    test_data = clean_data(test_data)

    svc = SVC(C=1000, gamma=.0001, kernel="rbf")

    train_model = TrainingModel(train_data, test_data, svc)
    train_model.evaluate(output=False)

    predictions = train_model.classifier.predict(train_model.test_data)
    results = ids.assign(Survived = predictions)
    results.to_csv("kaggle-titanic-results-svc.csv", index=False)


