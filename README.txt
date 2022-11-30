Design Changes:
What we changed from homework 4 was the commands, we now used the command design pattern for all of the commands which makes it easier to seperate it to ensure it does not clash with the model.
Moreover, from the feedback from the last assignment having a separate pixel class to check the pixels of each image should have been made a concrete class; hence, what we did to improve this was
utilize the java color which is given from Java.

We also made an abstract controller as there are two controllers one for the ppm images and one for the buffered image commands doing them in seperate classes would result in a lot of duplicate code; therefore, a abstract controller was made.

What has changed in the design from homework 5 was implementing the ImageRBGHistogram, MainImage, ImageGUIView, ImageGUIViewImpl, ImageGUIModel, ImageGUIModelImpl, ImageGUIController, ImageGUIControllerImpl which are all explained below.
All of these classes and interfaces have been added in order for the program to be displayed in a GUI and interact with the controller and model.


Model:
	ImageModel: 
	
The interface with the methods to be used in the model.

	ImageModelImpl:

The model is the where the ppm files are edited they hold the manipulation images in a hash map with the edited function applied. The model has the two methods which allows the user to either save or load the image given the path and name of the image. This is also where the image command is given and processes it with the image and pixels. 

    ImageIOModel:

The interface with the method to be used in the model for other file types, stores the buffered images.

    ImageIOModelImpl:

This class is very similar to ImageModelImpl; however, the difference between the two is that one is able to manipulate the exact pixel, whereas the new class manipulates bufferedImages and has the same methods.

    ImageGUIModel:

The interface with the method to be used in the GUI model.

    ImageGUIModelImpl:

The class which implements the interface it has the methods for the image in the GUI for the model.

View: 
	ImageView:

The interface for the view which has the methods of the view class

	ImageViewImpl:

The implementation of the methods which has the toString, renderMessage and renderImage functions. The toString shows the image to the render board which outputs the image. 

    ImageGUIView:

The interface for the GUI view which has the methods of the GUI view class

    ImageGUIViewImpl:

The implementation of the methods for the GUI view implementation which intializes the GUI and works
with the controller to apply the commands to all the images.

    ImageRBHHistogram:

The class which gets the image RGB value components and creates the histogram which needs to be implemented within the GUI.

    MainImage:

The class which displays the selected images by the user and allows the user to manipulate the images.

Controller:
	ImageController: 

The image controller interface which has the single public method which is called to run the program. 

	ImageControllerImpl:

Withholds the helper methods which helps with the main function to run the program. The controller runs all the commands given by the user, this edits the pixels of the given image with the given command, 

    AbstractController:

The abstract class which has the methods for the controller.

    ImageIOController:

This extends the abstract controller class and in the constructor puts all the methods for the buffered images.

    ImageGUIController:

The interface with all the controller methods for the GUI.

    ImageGUIControllerImpl:

Implements the ImageGUIController and extends the abstract controller class. In the constructor it
puts all the commands for the GUI controller. It works with the GUI view and provides the methods when called.


Commands:

    AbstractPPMCommands & AbstractIOCommands:

These were made in order to solve the issue of having duplicate code; however, two abstract classes needed to be made in order to reduce the duplicate code for each type of file, AbstractPPM being for ppm files
and AbstractIOCommands for other files types such as jpeg, jpg, bnp etc. This was done because of the different access methods for the different types of files and models as one of the models takes the Color to run the command on
whereas the other file types make sure that the ppm is turned into a buffer and then takes in a BufferImage and apply the commands.

	ImageCommands: 

The image commands interface which has the methods called on all of the command types 

	ImageState:

The command interface for images that are being horizontally flipped or vertically flipped 

	ImageStateImpl:

Implementation of the image state interface, which takes the image as a whole and rotates either vertically or horizontally, this is done differently as this uses the entire image to change the state. 

	Greyscale:

This has the commands for changing the colour of the image, for each of the RGB colour it has the ability to change the other corresponding pixels to the same value to make it grey. 

	Lighting:

This has the commands to either brighten the image or darken the image given a value to increase or decrease it by. This then takes the value of the rgb components of the pixel and increases each to make it brighter. 

    ImageLightingIO:

This is very similar to the normal lighting class which has the darken and brighten functions on a bufferedImage.

    ImageFilterIO:

This is the filter which applies the blur or sharpen the image, it takes in the BufferedImage and applies the matrix to each part of the bufferedImage.

    ImageGreyscaleIO:

Applies the sepiatone matrix and the greyscale matrix to the given rgb of the image for the bufferedImages.

    ImageStateIO:

Has the horizontal and vertical flip for the bufferedImage.

    ImageControlIO:

Saves and Loads the images.


Main: 
	-This class was made to run the program which takes in the controller with a model and view and runs the program given the different models and views. 

Pixel class:

    -The pixel class represents a single pixel that has the red, green and blue components which are then edited and called upon in the model to be edited or run upon. The class itself has return methods and calculations as provided in the assignment.

ImageUtil:

    -This has the methods which take in the ppm and has the methods to change it to a bufferImage, also has the ability to change a bufferImage back into ppm.
    similar to how we have the readPPMfile we needed to make one for the IO as well to read images of different file types.

Images Used:

    - snail.bmp
        citation: https://www.google.com/url?sa=i&url=https%3A%2F%2Fpeople.math.sc.edu%2FBurkardt%2Fdata%2Fbmp%2Fbmp.html&psig=AOvVaw2zJSSmwpFZGBWiwguTYEsx&ust=1669333345729000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCKCUn8G9xfsCFQAAAAAdAAAAABAF.

    - tiger.jpg
        citation: https://www.google.com/imgres?imgurl=https%3A%2F%2Fmedia.istockphoto.com%2Fid%2F1360055038%2Fphoto%2Fclose-up-view-portrait-of-a-siberian-tiger.jpg%3Fb%3D1%26s%3D170667a%26w%3D0%26k%3D20%26c%3D-nm1uu87whZtUQI3xObQQg6GaRY1wV-j6a5Ay9_pqkM%3D&imgrefurl=https%3A%2F%2Funsplash.com%2Fimages%2Fanimals%2Ftiger&tbnid=MH5UIeeidcj3NM&vet=12ahUKEwjC98aAv8X7AhXwm44IHYnLBOMQMyhIegUIARCBAQ..i&docid=j5wz8Q0r58pXjM&w=380&h=452&q=tiger%20jpg%5D&ved=2ahUKEwjC98aAv8X7AhXwm44IHYnLBOMQMyhIegUIARCBAQ

    - smallImage.ppm
        citation: https://www.google.com/imgres?imgurl=http%3A%2F%2Fweb.eecs.utk.edu%2F~ssmit285%2Fguide%2Fimg%2Fimg%2FTiny6pixel.png&imgrefurl=http%3A%2F%2Fweb.eecs.utk.edu%2F~ssmit285%2Fguide%2Fimg%2Findex.html&tbnid=G7tKWSpqWdojUM&vet=12ahUKEwjTubn2v8X7AhU2gI4IHegDChQQMygCegQIARBD..i&docid=48reNDb6aHxieM&w=192&h=128&q=Tiny%20pixel%20ppm&ved=2ahUKEwjTubn2v8X7AhU2gI4IHegDChQQMygCegQIARBD




 





 
