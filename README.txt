4D BYTE MAZE GENERATOR

The project creates a theoretical 4D Maze (doesn't actually draw one) which is output as a byte file. 
Each file contains characters equal to n^4, where is equivalent to the length of each side of the maze. This also results in the maze being a cube, which is then represented in another dimension (such as time). 

Furthermore, the maze meets two additional conditions:
• The maze is different every time, so a larger maze is not simply an expansion of a smaller one. A seed was not used but a time seed can simply solve this condition to be truly random.
• Each cell must be used, no cell can be completely blocked off (see an example where a byte is 11111111).
• A loop cannot exist in the maze, requiring the usage of disjoint sets to create a maze that can fully be represented as a tree. Each cell in the maze will be represented by a node which is capable of tracking it's own parent cell. At the start, each cell is it's own parent. Any cell merged into the forming maze will adopted a new parent (the parent node, aka the root of the entire tree). If the expanding maze attempts to expand to a cell which already shares a parent, then it will not destroy the dividing wall, thus preventing a loop from forming within the maze. 

------------------------------------------------------------------------------------------------------------------------------------------------

The resulting files will contain ASCII characters, each representing a byte which is converted to binary in order to be properly interpreted. Each bit in the converted value denotes whether or not a certain 'wall' is up or knocked down. Knocked down walls denote spaces where the maze is traversable from one cell to the next. The bits are read in pairs, in order, and contain the following rules:

• The order of the pairs define what axis they control.
	[xx][yy][zz][tt]
• 00: In this cell, along this coordinate axis, the player may move ±1 units.
	In other words, there are no walls barring movement along this axis.
• 01: In this cell, along this coordinate axis, the player may move in the -1 direction only. 
	There is a wall blocking the positive direction along thisaxis.
• 10: In this cell, along this coordinate axis, the player may move in the +1 direction only. 
	There is a wall blocking the negative direction along thisaxis.
• 11: In this cell, along this coordinate axis, the player may not move at all on this axis.
	There are walls blocking both the positive and negative direction alongthis axis.

tl;dr The '1' bits are walls. There are 0 to 255 different possibilities for a state of each individual cell.
------------------------------------------------------------------------------------------------------------------------------------------------

Examples:
A value of 'ï' is equivalent to 11101111, where movement is only possible on:
	the Y axis, in the positive direction.
A value of '»' is equivalent to 10111011, where movement is possible on:
	the X axis in the positive direction and
	the Z axis in the positive direction.

A value of "ÿ" is equivalent to 11111111, where movement is not possible at all.
A blank value (whitespace) is equiavalent to 00000000, where movement is accepted in all directions.

------------------------------------------------------------------------------------------------------------------------------------------------

Both the Driver and Node files are required, but only the Driver is to be executed within an IDE. Additional files include example outputs created from the application. 