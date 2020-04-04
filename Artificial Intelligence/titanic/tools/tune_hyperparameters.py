import os
import sys

sys.path.append(os.path.join(os.path.dirname(__file__), '../'))

from sklearn.model_selection import GridSearchCV
from sklearn.ensemble import RandomForestClassifier
from sklearn.svm import SVC

from titanic.model import TrainingModel
from titanic.data import clean_data
from titanic.utils import load_titanic_data

train_data, test_data = load_titanic_data()
train_data = clean_data(train_data)
test_data = clean_data(test_data)


model_list = [
    (
        RandomForestClassifier(
            max_features="auto", criterion="gini",
            oob_score=True, random_state=1, n_jobs=8,
        ),
        {
            "min_samples_leaf": [1, 2, 3],
            "min_samples_split": [2, 3, 4, 6, 8, 10, 15],
            "n_estimators": [50, 75, 100, 125, 150, 175, 200, 225, 250],
            "max_depth": [1, 3, 5, 10, 15, 20, 25, 30, 40, 50],
        }
    ),

    (
        SVC(random_state=42),
        [
            {'C': [1, 10, 100, 1000], 'kernel': ['linear']},
            {'C': [1, 10, 100, 1000], 'gamma': ["auto", 0.001, 0.0001], 'kernel': ['rbf']},
        ]
    )
]

for model in model_list:
    model, param_grid = model[0], model[1]

    trained_model = TrainingModel(train_data, test_data, model)
    clf = GridSearchCV(estimator=model, param_grid=param_grid, n_jobs=8)
    clf.fit(trained_model.x_train, trained_model.y_train)
    print(f"Best parameters for {trained_model.name}: {clf.best_params_}")
