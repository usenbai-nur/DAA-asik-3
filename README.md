#  City Transportation Network Optimization Using MST Algorithms

### Course: Design and Analysis of Algorithms  
**Author:** Nurdaulet Usenbay  
**Institution:** Astana IT University  
**Assignment:** 3 — *Minimum Spanning Tree Analysis*  

---

## Project Overview

This project implements and analyzes two fundamental algorithms for building **Minimum Spanning Trees (MST)** in weighted undirected graphs:  
- **Prim’s Algorithm**  
- **Kruskal’s Algorithm**

The algorithms are applied to a *city transportation network optimization problem* — connecting all districts with the **minimum total construction cost** and **no redundant roads**.

Input and output data are handled via JSON files (`ass_3_input.json` and `ass_3_output.json`).

---

##  Features

**Graph Parsing from JSON** — loads vertices & edges dynamically  
**Prim & Kruskal Implementations** — clean, efficient, fully tested  
**Metrics Tracking** — operation count + execution time + graph statistics  
**Graph Metrics Module** — density and weight distribution analysis  
**JSON Output Generator** — formatted results matching assignment spec  
**JUnit Tests** — validation of algorithm correctness  

---

## Algorithms Implemented

###  Prim’s Algorithm
- **Approach:** Vertex-based greedy  
- **Data Structure:** Priority Queue (Min-Heap)  
- **Complexity:** O(E log V)  
- **Best For:** Dense graphs  

###  Kruskal’s Algorithm
- **Approach:** Edge-based greedy  
- **Data Structure:** Union-Find (Disjoint Set)  
- **Complexity:** O(E log E)  
- **Best For:** Sparse graphs  

## Project structure:
DAAassignment3/
│
├── pom.xml
├── src/
│   ├── main/java/algorithms/
│   │   ├── Graph.java
│   │   ├── MSTApp.java
│   │   ├── Prim.java
│   │   ├── Kruskal.java
│   │   ├── MSTResult.java
│   │   └── GraphMetrics.java
│   └── test/java/algorithms/
│       └── MSTTest.java
│
├── src/main/resources/
│   ├── ass_3_input.json
│   └── ass_3_output.json
│
└── docs/
    └── report.md
Conclusion

Both algorithms produce optimal MSTs with identical total cost.

Kruskal performs slightly faster on sparse graphs.

Prim remains efficient for dense graphs.

The project demonstrates practical optimization for infrastructure design.
