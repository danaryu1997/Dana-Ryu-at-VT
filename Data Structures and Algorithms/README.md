# Data Structure and Algorithms (CS5040, Fall 2021)
The course covered data structures that are widely used in software development, as well as the associated algorithm analysis to determine the appropriate usage for the situation.

## Description

### Project 1 : SkipLists

Many applications areas such as computer graphics, geographic information systems, and VLSI design require the ability to store and query a collection of rectangles. In 2D, typical queries include the ability to find all rectangles that cover a query point or query rectangle, and to report all intersections from among the set of rectangles. Adding and removing rectangles from the collection are also fundamental operations.

In this project, a simple spatial database for handling inserting, deleting, and performing queries on a collection of rectangles is created. The data structure used to store the collection will be the Skip List. The Skip List fills the same role as a Binary Search Tree in applications that need to insert, remove, and search for data objects based on some search key such as a name. The Skip List is roughly as complex as a BST to implement, but it generally gives better performance since its worst case behavior depends purely on chance, not on the order of insertion for the data. Thus, the Skip List provides a good organization for answering non-spatial queries on the collection (in particular, for organizing the objects by name). 

#### Commands

* `insert {name} {x} {y} {w} {h}`
Insert a rectangle named name with upper left corner (x, y), width w and height h. 

* `remove {name}`
Remove the rectangle with name `name`.

* `remove {x} {y} {w} {h}`
Remove the rectangle with the specified dimensions.

* `regionsearch {x} {y} {w} {h}`
Report all rectangles currently in the database that intersect the query rectangle specified by the regionsearch parameters. 

* `intersections`
Report all pairs of rectangles within the database that intersect.

* `search {name}`
Return the information about the rectangle(s), if any, that have name `{name}`.

* `dump`
Return a “dump” of the Skip List. The Skip List dump should print out each Skip List node, from left to right. For each Skip List node, print that node’s value and the number of pointers that it contains (depth).


### Project2 : PR Quad Tree

The PR Quadtree is a full tree such that every node is either a leaf node, or else it is an internal node with four children. As with the PR Quadtree described in OpenDSA, the four children split the parent’s corresponding square into four equal-sized quadrants. The internal nodes of the PR Quadtree do not store data. Pointers to the points themselves are stored only in the leaf nodes.


#### Commands

* `insert {name} {x} {y}`
Insert a point named name with at the location (x, y).

* `remove {name}`
Remove the point with name name. 

* `remove {x} {y}`
Remove the point at the point {x} {y}. 

* `regionsearch {x} {y} {w} {h}`
Report all points currently in the database that are contained in the query rectangle specified by the 'regionsearch' parameters. For each such point, list out its name and coordinates. 

* `duplicates`

Report all points that have duplicate coordinates.


* `search {name}`
Return the information about the point(s), if any, that have name `{name}`.

* `dump`
Return a “dump” of the Skip List and the QuadTree.
