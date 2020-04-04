# Titanic

[Kaggle Titanic](https://www.kaggle.com/c/titanic)

Execute `run.py` to see the statistics on each model. They are outputted below:
```sh
------------------- Evaluating SVC -------------------
Mean scores from cross validation using SVC: 0.81
Confusion matrix using SVC: 
[[472  77]
 [ 90 252]]
Precision score using SVC: 0.77
Recall score using SVC: 0.74
Accuracy score using SVC: 0.81
ROC AUC score using SVC: 0.80

------------------- Evaluating RandomForestClassifier -------------------
Mean scores from cross validation using RandomForestClassifier: 0.84
Confusion matrix using RandomForestClassifier: 
[[502  47]
 [ 96 246]]
Precision score using RandomForestClassifier: 0.84
Recall score using RandomForestClassifier: 0.72
Accuracy score using RandomForestClassifier: 0.84
ROC AUC score using RandomForestClassifier: 0.82
```

After looking through the data, I noticed a few things:
  * The passenger id doesn't seem to matter in terms of survivability.
  * The ticket feature would be hard to use since the majority of the values in it are unique. 
  * The cabin feature is mostly missing values, but it does contain useful information
    in what deck people are on for the values it does have.
  * The names seem mostly meaningless, however, the titles in them could be useful.
  * The age feature is also missing about 177 values.
  
To combat this and clean up the data, I ended up:
  * Dropping the passenger id and ticket feature.
  * Convert the "cabin" feature into a "deck" feature and fill the empty values with 0.
  * Convert the names into a title feature.
  * Filled the remaining ages by using the mean age and randomly picking an age within the std.
  * Made sure all values were int64 types.

I created a tool to find the best model and saw that the RandomForestClassifier
was the best in that list so I stuck with it and ended up tuning both the SVC and 
RandomForestClassifier's hyper-parameters.

Precision vs Recall plots for both models are in the `plots/` directory, as well
as ROC curves. I found it interesting that the SVC model ended up being fairly 
comparable to the RandomForestClassifier because from the `tools/best_model.txt` file,
we can see SVC is one of the worst models before any sort of hyper-parameter tuning.

The similarity between the two in the end result is more clearly shown in the 
comparison roc curve in `plots/` where we see SVC is only slightly worse. 
I think one of the reasons SVC could be so comparable in this case is that all the data 
was converted over to int64s. Whereas Random Forests seems to do fine with numerical and 
categorical features and features on various scales, with an svm we have to do
one-hot encoding and other scaling for pre-processing steps. However, because of the
way the data was cleaned, the categorical pipeline was not necessary.

Oddly enough, the SVC scored higher on the test data when submitted to kaggle (77%) than
the RandomForestClassifier (75%).