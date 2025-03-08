import java.awt.Color;
import java.util.Random;

/**
 * Class BoxBall - A ball that moves inside a bounded box, bouncing off walls.
 *The ball has a random initial position and moves with a random intial velocity.
 *
 * @author Alejandro Olea
 * @version 3/7/2025
 */
public class BoxBall 
{
    private int xPos, yPos, xSpeed, ySpeed, diameter;
    private Color color;
    private Canvas canvas;
    private int leftWall, rightWall, topWall, bottomWall;

    // Constructor to initialize the ball within the box
    public BoxBall(int leftWall, int rightWall, int topWall, int bottomWall, Canvas canvas) 
    {
        this.leftWall = leftWall;
        this.rightWall = rightWall;
        this.topWall = topWall;
        this.bottomWall = bottomWall;
        this.canvas = canvas;

        // Set random position for the ball within the box
        Random rand = new Random();
        this.xPos = rand.nextInt(rightWall - leftWall - 20) + leftWall;
        this.yPos = rand.nextInt(bottomWall - topWall - 20) + topWall;

        // Set random speed for the ball
        do 
        {
            this.xSpeed = rand.nextInt(15) - 7; // Speed between -7 and 7
            this.ySpeed = rand.nextInt(15) - 7; // Speed between -7 and 7
        } while (xSpeed == 0 || ySpeed == 0); // Make sure the ball is moving

        // Random color for each ball
        this.color = new Color(rand.nextInt(200), rand.nextInt(200), rand.nextInt(200));
        this.diameter = 20; // Ball diameter
    }

    // Move the ball and bounce off walls
    public void move() 
    {
        // Erase the previous ball position (draw background color)
        canvas.eraseCircle(xPos, yPos, diameter);

        // Update the ball position
        xPos += xSpeed;
        yPos += ySpeed;

        // Bounce off the walls
        if (xPos <= leftWall || xPos >= rightWall - diameter) 
        {
            xSpeed = -xSpeed; // Reverse direction on x-axis
        }
        if (yPos <= topWall || yPos >= bottomWall - diameter) 
        {
            ySpeed = -ySpeed; // Reverse direction on y-axis
        }

        // Draw the ball at its new position
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPos, yPos, diameter);
    }
}