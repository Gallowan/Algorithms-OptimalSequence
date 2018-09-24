# Algorithms-OptimalSequence
Implements and tests the noted algorithm (in README) which computes an optimal sequence for performing a set of tests that does not violate a given precedence constrains on the tests sequencing. 

The precedence constraints are represented by an array of disjoined linked lists of test, where the ith test in a linked list is the immediate successor of the (i-1)th test in the same linked list. For each test, there are cost of testing i and probability of positive result . An optimal sequencing of tests is based on the minimizing the expected testing cost It has been proven that the following Greedy algorithm provides an optimal sequencing of tests.
Assume the array of linked lists is dented by L, where L[i][k] is the kth test in the linked list L[i] and the its immediate predecessors are L[i}{0}, L[i}[1],….L[i][k-1].  Let C[i][k] and P[i][k] be the cost and probability associated with the test L[i][k]
The Algorithm 
1. Let OP denotes an optimal sequence and set it to the empty string;
2. While (the linked lists are not completely empty), Do the following four steps:
3. For each test L[i][j], compute the ratio R[i][j] as follows:
   R[i][j] =  ((∑_(k=0)^j▒〖C[i][k]〗))/((∑_(k=0)^j▒〖P[i][k]〗) )
4. Choose the test with the smallest R ratio and has no predecessor test with the same R ratio; 
5. Add this test with its predecessor’s tests to the optimal sequence OP;
6. Remove this test and its predecessors from its linked list.
7. Return OP.


The program begins by prompting the user to create lists using their own parameters, including name, cost and probability.
Once the user is done creating lists, the program takes the lists provided and returns the most optimal sequence, denoted as “OP” as stated in the instructions.

Followed is a test print out using randomized numbers, followed by the code itself.

Enter either: the name of the test or '0' to finish the current list.

1

Enter test cost: 

5

Enter test probability: 

10

X - 1 5 10.0

Enter either: the name of the test or '0' to finish the current list.

2

Enter test cost: 

14

Enter test probability: 

25

X - 1 5 10.0

X - 2 14 25.0

Enter either: the name of the test or '0' to finish the current list.

3

Enter test cost: 

23

Enter test probability: 

75

X - 1 5 10.0

X - 2 14 25.0

X - 3 23 75.0

Enter either: the name of the test or '0' to finish the current list.

4

Enter test cost: 

32

Enter test probability: 

64

X - 1 5 10.0

X - 2 14 25.0

X - 3 23 75.0

X - 4 32 64.0

Enter either: the name of the test or '0' to finish the current list.

5

Enter test cost: 

23

Enter test probability: 

45

X - 1 5 10.0

X - 2 14 25.0

X - 3 23 75.0

X - 4 32 64.0

X - 5 23 45.0

Enter either: the name of the test or '0' to finish the current list.

0



Lists:

-> 1 5 10.0



-> 2 14 25.0



-> 3 23 75.0



-> 4 32 64.0



-> 5 23 45.0



Please enter 1 (move to another list) or 0 (stop making lists).

0

Optimal Sequence:



X - 1 5 10.0

X - 2 14 25.0

X - 3 23 75.0

X - 4 32 64.0

X - 5 23 45.0



Process finished with exit code 0
