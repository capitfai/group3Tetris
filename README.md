### **group3Tetris**
### **Group 3 Section A**

### Group Members
- Faith Capito
- Josh Chang
- Avreen Kaur
- Sullivan Lucier-Benson

### Sprint 1 Contributions
- Faith & Avreen: Sectioned out panels in the GUI window where the game will be storing the board, the next piece, and miscellaneous information.
- Josh & Sullivan: Worked on File Menu of GUI with corresponding game menu items with clickable buttons.

### Sprint 1 Comments
There was a bit of struggle getting Git to work as intended but overall we are satisfied with what we have so far!

### Sprint 2 Contributions
- Faith: Implemented and instantiated swing timer and KeyListeners to upper-most GUI panels in view. Added reference to Board object.
- Avreen: Updated model to define Board's API and instantiate PropertyChangeSupport and PropertyChangeListeners.
- Sullivan: Implement PropertyChangelisteners into Tetris Board while adding object into list of PropertyChangeListeners in Board object.
- Josh: Implement PropertyChangeListeners into Next Tetris Piece panel while adding object into list of PropertyChangeListeners in Board object.

### Sprint 2 Meetings
Link to Meeting Minutes: https://docs.google.com/document/d/15f6RJXX8kMjsD0ToOihwurH-tNYxQHva_NXm1pcW0CA/edit?usp=sharing

### Sprint 2 Comments
We hope PropertyChangeListeners and KeyAdapters are somewhat correct as they do respond to each other but do not yet follow behaviors we want them to take.


### Sprint 3 Contributions
- Faith: Visually enhanced the panels to represent a classic arcade-like version of Tetris.
- Avreen: Implemented and worked out the logic behind the game board such as drawing/animating the pieces and handling the informational panels.
- Sullivan: Work out the logic to implement different levels in Tetris game.
- Josh: Ensure implented classes properly model a Observer Design Pattern.

### Sprint 3 Meetings
Link to Meeting Minutes: https://docs.google.com/document/d/15f6RJXX8kMjsD0ToOihwurH-tNYxQHva_NXm1pcW0CA/edit?usp=sharing

### Sprint 3 Comments
Resources:
- PixelMPlus font that represents classic 12-bit feel (https://itouhiro.hatenablog.com/entry/20130602/font)

### Required Line Locations

*Location that determines how many more lines until next level and class that contains scoring algorithm*
View class "bluePanel", line 387

*Change in Board class that allows us to set myGameOver to false*
Model class "Board", setGameOver() method is changed with property change support to be able to set our instance field in GUI.
