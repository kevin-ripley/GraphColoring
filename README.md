# GraphColoring
# CSCI 446 A.I. Lab 1
# Artificial Intelligence
# Project #1
Search, Constraint Satisfaction, and Graph Coloring
The purpose of this assignment is to give you an introduction to artificial intelligence from the perspectiveof search. Being that this is a team project, several elements will need to be implemented. This project is avariation and extension of the problem in the textbook (Russell & Norvig, 6.10).For this project, you need to write a “problem generator” whereby you will create several graphs of various sizes to solve instances of the graph-coloring problem. For your graph generator, scatter n points on the unitsquare (where n will be provided as input), select some point X at random and connect X by a straight line to the nearest point Y such that X is not already connected to Y and line crosses no other line. Repeat the previous step until no more connections are possible. The points represent regions on the map, and the linesconnect neighbors.After building the graph, try to find k-colorings of each map for both k=3 and k=4 using min-conflicts,backtracking, backtracking with forward checking, and backtracking with MAC. You will also attempt to find such colorings using a genetic algorithm with tournament selection and a fitness function with a penalty term.Here are the specific steps that need to be followed:
Implement the graph generator.
Generate at least 10 different graphs of sizes ranging from 10 to 100 vertices in steps of 10 (i.e., 10, 20,30, 40, 50, 60, 70, 80, 90, 100).
Implement a constraint solver to find 3-colorings and 4-colorings for each graph (if they exist), imple-menting the following variations:
–Min conflicts–Simple backtracking–Backtracking with forward checking–Backtracking with constraint propagation (MAC)–Local search using a genetic algorithm with a repair function and tournament selection
