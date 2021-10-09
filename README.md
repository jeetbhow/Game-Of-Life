# Conway's Game of Life

## What's the Game of Life?

Welcome to the *Game of Life*! The Game of Life is a type of
*cellular automata* that was devised by English mathematician 
John Horton Conway in 1970. You have a grid of cells in 
which each cell exist in one of two states: dead or alive. 
Every iteration of the game, cells exchange information
with their neighbors and update their own state according
to the state of their neighbors. This can lead to the 
spontaneous emergence of some interesting patterns and 
structures.

The inspiration for this project came 
after watching a talk called *The Art of Code* 
by Dylan Beattie. It's about how code
can be art. This is where I saw a demonstration of
The Game of Life, and I've been itching to write 
my own implementation ever since. 

In case you're interested, The Art of Code can be 
seen here: https://www.youtube.com/watch?v=6avJHaC3C2U&t=79s

## The Rules

The rules are really simple. We just answer two questions: 
what keeps a cell alive, and what keeps it dead?

- Live cells require exactly 2 or 3 live neighbors in order
to survive. Anything else will cause it to die by either
  *overpopulation* or *underpopulation*. 
  
- Dead cells can be revived if surrounded by three live 
neighbors through *reproduction*. Otherwise, it stays dead. 

That's it. It's pretty simple. 

## So what does *this* program do specifically?

I'm glad you asked! This program is a graphical simulation
of the Game of Life. You can set the size of the grid, the
amount of time passed per iteration, the initial state of
the system, colors, and other things. The list of features
can be found in the user stories which I'll update 
throughout the project. 

## User Stories

- As a user, I want to be able to add cells to my game 
board.
  
- As a user, I want to be able to set the initial state of
the system by choosing which cells are dead and alive.
  
- As a user, I want to be able to pause the simulation and
modify it's state at any point. 