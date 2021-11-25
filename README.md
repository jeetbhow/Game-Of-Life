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
structures. For more information, please visit 
https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life

The inspiration for this project came 
after watching a talk called *The Art of Code* 
by Dylan Beattie. It's about how code
can be art. This is where I saw a demonstration of
The Game of Life, and I've been itching to write 
my own implementation ever since. In case you're interested, 
The Art of Code can be seen here:
https://www.youtube.com/watch?v=6avJHaC3C2U&t=79s

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

This program is a graphical simulation
of the Game of Life. The list of features
can be found in the user stories which I'll update 
throughout the project.

## Phase 4: Task 2
A sample of events that were logged in the Event Log. 

![img.png](img.png)

## User Stories

- As a user, I want to be able to add a row of cells to the board. 

- As a user, I want to be able to add a column of cells to the board.
  
- As a user, I want to be able to set the state of individual cells
on the board.
  
- As a user, I want to be able to randomize the board. 

- As a user, I want to be able to save the state of the board to a file. 

- As a user, I want to be able to load the state of the board from a file and
resume the simulation. 

## Updates
- Nov 25, 2021: Finished GUI. 
- Oct 12, 2021: Finished a console based implementation.
- Nov 7, 2021: Added the ability to save and pick off from
where you left off. 
  