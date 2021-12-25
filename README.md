# Conway's Game of Life

## What's the Game of Life?

Welcome to the *Game of Life*! The Game of Life is a type of
*cellular automata* that was devised by English mathematician 
John Horton Conway in 1970. You have a grid of cells in 
which each cell exists in one of two states: dead or alive. 
Every iteration of the game, cells update their state based
on the state of their neighbors. This can lead to 
some pretty interesting patterns. 

This is a project that I worked on for CPSC 210: Software Construction at
the University of British Columbia. The course included the design of a 
project which contributes to a quarter of your grade. 
The inspiration for this project came 
after watching a talk called *The Art of Code* which can be seen here. 

https://www.youtube.com/watch?v=6avJHaC3C2U&t=79s

## The Rules

The rules are simple. We answer two questions: 
what keeps a cell alive, and what keeps it dead?

- Live cells require exactly 2 or 3 live neighbors in order
to survive. Anything else will cause it to die.
  
- Dead cells can be revived if surrounded by three live 
neighbors. Otherwise, it stays dead. 
  

## Updates
- Nov 25, 2021: Finished GUI. 
- Oct 12, 2021: Finished a console based implementation.
- Nov 7, 2021: Added the ability to save and pick off from
where you left off. 
  