import numpy as np


class ConfusionMatrixStats:
    def __init__(self, matrix: np.array):
        self.matrix = matrix
        self.TN = matrix[0][0]
        self.FP = matrix[0][1]
        self.FN = matrix[1][0]
        self.TP = matrix[1][1]

    def precision(self):
        return self.TP / (self.TP + self.FP)

    def recall(self):
        return self.TP / (self.TP + self.FN)

    def accuracy(self):
        err = (self.FN + self.FP) / (self.TN + self.FP + self.FN + self.TP)
        return 1 - err
