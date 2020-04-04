import numpy as np
from sklearn.datasets import fetch_openml
from sklearn.ensemble import ExtraTreesClassifier, RandomForestClassifier
from sklearn.metrics import accuracy_score, confusion_matrix
from sklearn.model_selection import train_test_split
from sklearn.svm import LinearSVC
from xgboost import XGBClassifier

if __name__ == "__main__":
    mnist = fetch_openml("mnist_784", version=1)
    mnist.target = mnist.target.astype(np.uint8)

    X_train_val, X_test, y_train_val, y_test = train_test_split(
        mnist.data, mnist.target, test_size=10000, random_state=703111
    )
    X_train, X_val, y_train, y_val = train_test_split(
        X_train_val, y_train_val, test_size=10000, random_state=703111
    )

    random_forest_clf = RandomForestClassifier(
        n_estimators=100, random_state=703111, n_jobs=-1
    )
    extra_trees_clf = ExtraTreesClassifier(
        n_estimators=100, random_state=703111, n_jobs=-1
    )
    svm_clf = LinearSVC(random_state=703111, dual=False)
    xgb_clf = XGBClassifier(random_state=703111)

    estimators = [random_forest_clf, extra_trees_clf, svm_clf, xgb_clf]
    for estimator in estimators:
        print(f"Training {estimator.__class__.__name__}")
        estimator.fit(X_train, y_train)

        print()

        y_pred = estimator.predict(X_val)
        print(f"Accuracy score for validation: {accuracy_score(y_val, y_pred)}")
        print(f"Confusion matrix for validation:")
        print(confusion_matrix(y_val, y_pred))

        print()

        y_pred = estimator.predict(X_test)
        print(f"Accuracy score for test: {accuracy_score(y_test, y_pred)}")
        print("Confusion matrix for test: ")
        print(confusion_matrix(y_test, y_pred))

        print()
