# Problem Statement
There are two kinds of threads: men and women. A unisex bathroom having `BATHROOM_SIZE` capacity is available.
1. Every thread, after signaling need of using the bathroom, should wait until it will be able to use the bathroom.
2. Prevent any situation when more than `BATHOOM_SIZE` threads are using the bathroom in parallel.
3. Prevent woman and man from using the bathroom at the same time.
4. Threads should use bathroom concurrently. If there are many threads of one type, up to `BATHROOM_SIZE` threads 
should use the bathroom.
5. Prevent starvation.

## Solution Approach
1. Prevent starvation by implementing a first-come-first-served policy. A queue has been used for this purpose.
2. A person is considered eligible if they are the first in the queue and either the bathroom is empty or they are of 
the same gender as the other users of the bathroom.
3. If the person is not eligible, make them `wait()` for their turn.
4. When a person exits the queue, the next person waiting in the queue is notified to check if they are eligible.
5. The variable `users` keeps track of the number of current users of the bathroom, and variable `userType` tracks the
gender of the current users.

## Sample output
```
Bathroom capacity: 5, men: 14, women: 12
Man-1 entered the bathroom.
Man-14 entered the bathroom.
Man-14 left the bathroom.
Man-1 left the bathroom.
Woman-12 entered the bathroom.
Woman-11 entered the bathroom.
Woman-10 entered the bathroom.
Woman-10 left the bathroom.
Woman-12 left the bathroom.
Woman-11 left the bathroom.
Man-13 entered the bathroom.
Man-13 left the bathroom.
Woman-9 entered the bathroom.
Woman-9 left the bathroom.
Man-12 entered the bathroom.
Man-11 entered the bathroom.
Man-11 left the bathroom.
Man-12 left the bathroom.
Woman-8 entered the bathroom.
Woman-7 entered the bathroom.
Woman-6 entered the bathroom.
Woman-5 entered the bathroom.
Woman-8 left the bathroom.
Woman-5 left the bathroom.
Woman-6 left the bathroom.
Woman-7 left the bathroom.
Man-10 entered the bathroom.
Man-10 left the bathroom.
Woman-4 entered the bathroom.
Woman-4 left the bathroom.
Man-9 entered the bathroom.
Man-8 entered the bathroom.
Man-7 entered the bathroom.
Man-7 left the bathroom.
Man-8 left the bathroom.
Man-9 left the bathroom.
Woman-3 entered the bathroom.
Woman-3 left the bathroom.
Man-6 entered the bathroom.
Man-6 left the bathroom.
Woman-2 entered the bathroom.
Woman-2 left the bathroom.
Man-5 entered the bathroom.
Man-4 entered the bathroom.
Man-4 left the bathroom.
Man-5 left the bathroom.
Woman-1 entered the bathroom.
Woman-1 left the bathroom.
Man-3 entered the bathroom.
Man-2 entered the bathroom.
Man-2 left the bathroom.
Man-3 left the bathroom.
All 26 people have used the bathroom.
```