## Image Processing Software

This project was created at Northeastern University with Emily Lin

## ASSIGNMENT 6
The original parts of the program from the previous two assignments are complete. The new GUI supports
loading and saving multiple images and editing them using flips, component visualization, greyscale,
blur, sharpen, and sepia. The new GUI also shows the histogram of an image and updates the histogram
when the image is changed. The GUI shows one image at a time (the image currently being worked on), but 
multiple images can be loaded in and stored in the program to be edited. The time it takes to load an
image and edit an image depends on the size of the image, with smaller images taking less time to load
and edit. If the image is bigger than the area allocated to it in the GUI, a scroll pane will show up.

Our program works with all 3 ways of running it (executing a script file, interactive text mode, and GUI).

View
We created a new View interface that contains the operations offered by the Java Swing GUI. The JFrameView
class implements this interface and is called to create the GUI.

Controller
We created a new controller to handle the program in GUI mode. This new controller takes in a controller
that handles the program in script mode and calls its load and save methods.

Citations
The "flower.jpg" image is from https://commons.wikimedia.org/wiki/File:T_albidum01.jpg and has a
Creative Commons license, so we are legally allowed to use it in our project. 
The "Image.ppm" image that all the resulting images were produced on were created by
us through TextEdit and visualized through Preview. We authorize its use in our project.

## ASSIGNMENT 5
All parts of the program are complete, including the newly added abilities of parsing a script file and
applying a transformation matrix to filter an image, as well as the original parts of the program
from the last assignment.

Controller
We changed the controller's start() method to follow the command design pattern and reduce code
duplication. We mapped all text commands to their corresponding method in a map of function objects
to completely remove the switch statement. Now, since the details of each command are kept in separate
classes, we can add support for new text commands in the future by simply adding a new entry to the map.

Model
We used to have an ImageModelImpl contain both a single image and a map of all images. To separate the
functionality, we created a new interface, ImageLibrary, to represent a group of images and changed
the ImageModel interface to only represent a single image. The ImageLibrary contains the getImage() and
addImage() methods that the ImageModel had in the last assignment. Now, the user does not have to set the
image being used before changing it, and each class only represents one thing.

## ASSIGNMENT 4
Controller
The Controller interface represents the controller for the image processor.
It allows the user to start the image processing program using the start() method.

The ControllerImpl class inherits the methods in the Controller interface.
Its constructor takes in a model and Readable object as inputs and throws an IllegalArgumentException
if either the model or Readable object is null.
The start() method supports user commands to load, brighten, flip vertically, flip horizontally, and
greyscale a PPM image using the red, green, and blue components or the value, intensity, and luma
representations.
If an operation is successfully performed, the program will display a message that the operation was
completed. The start() method also allows the user to quit the program if they input “q”.
If the user inputs an invalid command (e.g. the text command was inputted inaccurately), the program
will display “Invalid input” and the user can type in another command.

To load an image into the image processor, the client inputs "load", the path of the image they want
to load, and the name they want the image to be called in the command line.
After loading, the client can choose to brighten or darken, flip horizontally or vertically, save
the image, or greyscale the image using either the red, green, or blue component or the value,
intensity, or luma representation.

The client can request to brighten or darken an image by inputting "brighten", the integer increment
they want to brighten/darken the image by, the name of the image they want to change, and the name
they want the new image to be called in the command line.
If the inputted integer increment is positive, the image will be brightened. If the inputted integer
increment is negative, the image will be darkened.

The client can request to convert an image to greyscale by inputting “[color/representation]-component”,
the name of the image they want to change, and the name they want the new image to be called in the
command line, where [color/representation] can be either red, green, blue, value, intensity, or luma.

The client can request to flip an image horizontally by inputting "horizontal-flip", the name of the
image they want to change, and the name they want the new image to be called in the command line.
To flip an image vertically, the client can input "vertical-flip", the name of the image they want
to change, and the name they want the new image to be called in the command line.

The client can request to save a PPM image by inputting "save", the path the image should be saved
to, and the name of the image to save in the command line.

The Command class contains the main method. The client uses the main method to run the program and
type in commands in the command line.


Model
The ImageModel interface represents the operations offered by the image processor.
This interface supports the image editing operations mentioned in the controller and allows multiple
images to be stored in a map; each image can be accessed through the getImage method.
The ImageModelImpl class represents a PPM image processor and inherits methods from ImageModel.
Once an image is loaded through the controller, the operations in this class can be applied to the
image to change it.
Multiple images can be stored in a map where each image name (key) is mapped to its Pixel[][]
representation (value).
We chose to represent images as a 2D Pixel array where every 1D Pixel array in the 2D array
represents a row of Pixels in an image.


Pixel
The Pixel class represents a single pixel, which contains red, green, and blue components. Its
methods allow getting and setting a pixel’s component values.


Script of Commands
Copy and paste the commands line by line from the script file when the program runs.


Citation
The given image that is res/Image.ppm that all the resulting images were produced on were created by
us through TextEdit and visualized through Preview. We authorize its use in our project.
