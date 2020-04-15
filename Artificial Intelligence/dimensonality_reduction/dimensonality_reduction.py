from collections import Counter

import matplotlib.pyplot as plt
import numpy as np
from sklearn.cluster import KMeans
from sklearn.datasets import fetch_olivetti_faces
from sklearn.decomposition import PCA
from sklearn.metrics import (
    homogeneity_completeness_v_measure,
    silhouette_score
)
from sklearn.metrics.cluster import contingency_matrix
from tabulate import tabulate


def find_best_k(faces):
    """
    Find the best value for k in a k means model given a dataset.

    This uses the silhouette score to find the best k and shows a plot 
    of all the models scores.
    """
    k_range = range(5, 150, 5)
    k_means_per_k = []
    for k in k_range:
        print(f"k={k}")
        kmeans = KMeans(n_clusters=k, random_state=42).fit(faces)
        k_means_per_k.append(kmeans)

    silhouette_scores = [
        silhouette_score(faces, model.labels_) for model in k_means_per_k
    ]
    best_index = np.argmax(silhouette_scores)
    best_k = k_range[best_index]
    best_score = silhouette_scores[best_index]
    print(f"Best k: {best_k}")

    plt.figure(figsize=(8, 3))
    plt.plot(k_range, silhouette_scores, "bo-")
    plt.xlabel("$k$", fontsize=14)
    plt.ylabel("Silhouette score", fontsize=14)
    plt.plot(best_k, best_score, "rs")
    plt.show()


def show_cluster(faces, labels, n_cols=5):
    """Plots a given cluster. Used for the initial run."""
    n_rows = (len(faces) - 1) // n_cols + 1
    plt.figure(figsize=(n_cols, n_rows * 1.1))
    for index, (face, label) in enumerate(zip(faces, labels)):
        plt.subplot(n_rows, n_cols, index + 1)
        plt.imshow(face.reshape(64, 64), cmap="gray")
        plt.axis("off")
        plt.title(label)
    plt.show()


def show_gallery(pca, transformed_data, target, kmeans):
    """Plot the entire gallery of clusters."""
    train_approx = pca.inverse_transform(transformed_data)
    for cluster_id in np.unique(kmeans.labels_):
        print("Cluster", cluster_id)
        in_cluster = kmeans.labels_ == cluster_id
        faces = train_approx[in_cluster].reshape(-1, 64, 64)
        labels = target[in_cluster]
        show_cluster(faces, labels)


def initial_run():
    """
    Used for the initial run section of the writeup
    where an initial guess was used with 0.95 PCA value.
    """
    olivetti = fetch_olivetti_faces()
    train = olivetti.data

    pca = PCA(0.95)
    transformed_data = pca.fit_transform(train)

    kmeans = KMeans(n_clusters=100, random_state=42).fit(transformed_data)
    show_gallery(pca, transformed_data, target, kmeans)


def total_useful_clusters(model):
    """A useful cluster here is defined as being a cluster with > 1 member."""
    clusters = Counter(model.labels_)
    useful_clusters = 0

    for cluster_num, total_members in clusters.items():
        if total_members > 1:
            useful_clusters += 1

    return useful_clusters


if __name__ == "__main__":
    olivetti = fetch_olivetti_faces()
    train = olivetti.data
    target = olivetti.target
    pca_values = [0.85, 0.90, 0.95, 0.99, 0.999, 0.9999]
    k_values = [40, 50, 75, 90, 95, 100, 105, 110, 115, 120, 125, 130, 150, 200]

    table = []
    headers = [
        "PCA",
        "K",
        "Homogeneity score",
        "Completeness Score",
        "V-measure Score",
        "Silhouette Score",
        "# of Clusters with > 1 member",
    ]

    for pca_val in pca_values:
        print(f"On current pca value of {pca_val}")
        for k in k_values:
            pca = PCA(pca_val)
            transformed_data = pca.fit_transform(train)
            kmeans = KMeans(n_clusters=k, random_state=42).fit(transformed_data)
            homogeneity, completeness, v_measure = homogeneity_completeness_v_measure(
                target, kmeans.labels_
            )
            table.append(
                [
                    pca_val,
                    k,
                    "%.4f" % homogeneity,
                    "%.4f" % completeness,
                    "%.4f" % v_measure,
                    "%.4f" % silhouette_score(train, kmeans.labels_),
                    total_useful_clusters(kmeans),
                ]
            )

    print(tabulate(table, headers, tablefmt="github"))
