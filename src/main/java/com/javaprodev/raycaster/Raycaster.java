package com.javaprodev.raycaster;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

/**
 * Created by X on 8/13/2017.
 */
public class Raycaster extends BasicGame {
    private int mapWidth = 24;
    private int mapHeight = 24;
    private int[][] map =
            {
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 4, 4, 6, 4, 4, 6, 4, 6, 4, 4, 4, 6, 4},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {8, 0, 3, 3, 0, 0, 0, 0, 0, 8, 8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {8, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {8, 0, 3, 3, 0, 0, 0, 0, 0, 8, 8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 4, 0, 0, 0, 0, 0, 6, 6, 6, 0, 6, 4, 6},
                    {8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 4, 4, 4, 4, 4, 4, 6, 0, 0, 0, 0, 0, 6},
                    {7, 7, 7, 7, 0, 7, 7, 7, 7, 0, 8, 0, 8, 0, 8, 0, 8, 4, 0, 4, 0, 6, 0, 6},
                    {7, 7, 0, 0, 0, 0, 0, 0, 7, 8, 0, 8, 0, 8, 0, 8, 8, 6, 0, 0, 0, 0, 0, 6},
                    {7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 6, 0, 0, 0, 0, 0, 4},
                    {7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 6, 0, 6, 0, 6, 0, 6},
                    {7, 7, 0, 0, 0, 0, 0, 0, 7, 8, 0, 8, 0, 8, 0, 8, 8, 6, 4, 6, 0, 6, 6, 6},
                    {7, 7, 7, 7, 0, 7, 7, 7, 7, 8, 8, 4, 0, 6, 8, 4, 8, 3, 3, 3, 0, 3, 3, 3},
                    {2, 2, 2, 2, 0, 2, 2, 2, 2, 4, 6, 4, 0, 0, 6, 0, 6, 3, 0, 0, 0, 0, 0, 3},
                    {2, 2, 0, 0, 0, 0, 0, 2, 2, 4, 0, 0, 0, 0, 0, 0, 4, 3, 0, 0, 0, 0, 0, 3},
                    {2, 0, 0, 0, 0, 0, 0, 0, 2, 4, 0, 0, 0, 0, 0, 0, 4, 3, 0, 0, 0, 0, 0, 3},
                    {1, 0, 0, 0, 0, 0, 0, 0, 1, 4, 4, 4, 4, 4, 6, 0, 6, 3, 3, 0, 0, 0, 3, 3},
                    {2, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 1, 2, 2, 2, 6, 6, 0, 0, 5, 0, 5, 0, 5},
                    {2, 2, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 2, 2, 0, 5, 0, 5, 0, 0, 0, 5, 5},
                    {2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 5, 0, 5, 0, 5, 0, 5, 0, 5},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 5, 0, 5, 0, 5, 0, 5, 0, 5},
                    {2, 2, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 2, 2, 0, 5, 0, 5, 0, 0, 0, 5, 5},
                    {2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 5, 5, 5, 5, 5, 5, 5, 5, 5}
            };

    private float FOV = 60;
    private int playerHeight = 32;
    private int wallHeight = 64;
    private float playerPosX = 300;
    private float playerPosY = 127;
    private int playerSpeed = 4;
    private int projectionPlaneWidth = 640;
    private int projectionPlaneHeight = 480;
    private float playerDirectionInDegrees = 30;

    private Vector2f centerOfProjectionPlane = new Vector2f(projectionPlaneWidth / 2, projectionPlaneHeight / 2);
    private float angleBetweenSubsequentRays = FOV / projectionPlaneWidth;
    private float distanceBetweenPlayerAndProjectionPlane = (projectionPlaneWidth / 2) / (float) tan(toRadians(FOV / 2));


    private List<Rectangle> walls = new ArrayList<Rectangle>();
    private List<Rectangle> visibleWalls = new ArrayList<Rectangle>();
    private List<Line> verticalRays = new ArrayList<Line>();
    private List<Float> rayDistanceToWall = new ArrayList<Float>();
    private List<Float> anglesOfRaysInDegrees = new ArrayList<Float>();
    private List<Integer> pixelPositionUnderWall = new ArrayList<Integer>();
    private GeomUtil geomUtil = new GeomUtil();
    private Image[] texture = new Image[9];

    private Image skyBackground;

    Color[][] floorTextureArray = new Color[64][64];


    public Raycaster(String title) {

        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

        skyBackground = new Image("src/main/resources/sky.png");


        for (int i = 0; i < projectionPlaneWidth; i++) {
            verticalRays.add(new Line(playerPosX, playerPosY));
            rayDistanceToWall.add(0f);
            anglesOfRaysInDegrees.add(0f);
            pixelPositionUnderWall.add(0);
        }

        for (int row = 0; row < mapHeight; row++) {
            for (int col = 0; col < mapWidth; col++) {
                if (map[col][row] != 0) {
                    walls.add(new Rectangle(row * 64, col * 64, 64, 64));
                }
            }
        }
        initTextures();
        initFloorTextureArray();

    }

    private void initFloorTextureArray(){
        for(int i = 0; i< 64;i++){
            for(int j = 0; j<64;j++){
                floorTextureArray[i][j] = texture[0].getColor(i,j);
            }
        }
    }

    private void initTextures() {
        try {
            texture[0] = new Image("src/main/resources/floor.png");
            texture[1] = new Image("src/main/resources/wall.png");
            texture[2] = new Image("src/main/resources/wall.png");
            texture[3] = new Image("src/main/resources/wall.png");
            texture[4] = new Image("src/main/resources/wall.png");
            texture[5] = new Image("src/main/resources/wall.png");
            texture[6] = new Image("src/main/resources/wall.png");
            texture[7] = new Image("src/main/resources/wall.png");
            texture[8] = new Image("src/main/resources/wall.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {


        processKeyboard(gameContainer);
    }

    private void processKeyboard(GameContainer gameContainer) {
        if (gameContainer.getInput().isKeyDown(Input.KEY_A)) {
            rotatePlayer(-1);
        }
        if (gameContainer.getInput().isKeyDown(Input.KEY_D)) {
            rotatePlayer(1);
        }
        if (gameContainer.getInput().isKeyDown(Input.KEY_W)) {
            playerPosX = playerPosX + (float) Math.cos(toRadians(playerDirectionInDegrees)) * playerSpeed;
            playerPosY = playerPosY + (float) Math.sin(toRadians(playerDirectionInDegrees)) * playerSpeed;
        }
        if (gameContainer.getInput().isKeyDown(Input.KEY_S)) {
            playerPosX = playerPosX - (float) Math.cos(toRadians(playerDirectionInDegrees)) * playerSpeed;
            playerPosY = playerPosY - (float) Math.sin(toRadians(playerDirectionInDegrees)) * playerSpeed;
        }
    }

    private void rotatePlayer(int change) {
        if (change > 0) {
            if (playerDirectionInDegrees == 360) {
                playerDirectionInDegrees = 0;
            } else {
                playerDirectionInDegrees += playerSpeed;
            }
        } else {
            if (playerDirectionInDegrees == 0) {
                playerDirectionInDegrees = 360;
            } else {
                playerDirectionInDegrees -= playerSpeed;
            }
        }
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        skyBackground.draw();
        long startTime = System.nanoTime();

        wallRayCast();
        floorRayCast(graphics);
        boolean renderMap = false;

        if (renderMap) {
            graphics.setColor(Color.gray);
            renderMap(graphics);

            float endX = playerPosX + (float) Math.cos(toRadians(playerDirectionInDegrees)) * 2000;
            float endY = playerPosY + (float) Math.sin(toRadians(playerDirectionInDegrees)) * 2000;
            Line player = new Line(playerPosX,playerPosY,endX,endY);
            graphics.draw(player);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        graphics.setColor(Color.white);
        graphics.drawString(duration+" ms", 10,25);
    }

    private void wallRayCast() {
        float endX;
        float endY;
        float projectedSliceHeight;
        float correctedDistance;
        int xOffset = 0;
        int yOffset = 0;

        float angleForRayInDegrees = -FOV / 2;
        int viewDistance = 2000;
        Rectangle intersectedWall = null;

        for (int col = 0; col < projectionPlaneWidth; col++) {
            endX = playerPosX + (float) Math.cos(toRadians(playerDirectionInDegrees + angleForRayInDegrees)) * viewDistance;
            endY = playerPosY + (float) Math.sin(toRadians(playerDirectionInDegrees + angleForRayInDegrees)) * viewDistance;
            Line ray = verticalRays.get(col);
            ray.set(playerPosX, playerPosY, endX, endY);
            anglesOfRaysInDegrees.set(col, angleForRayInDegrees);
            for (Rectangle wall : walls) {
                if (ray.intersects(wall)) {
                    GeomUtil.HitResult hitResult = geomUtil.intersect(wall, ray);
                    ray.set(playerPosX, playerPosY, hitResult.pt.getX(), hitResult.pt.getY());
                    rayDistanceToWall.set(col, ray.length());
                    xOffset = (int) hitResult.pt.getX() % 64;
                    yOffset = (int) hitResult.pt.getY() % 64;
                    intersectedWall = wall;

                }
            }

            int textureIndex = 1;
            if (intersectedWall != null) {
                textureIndex = map[(int) intersectedWall.getY() / 64][(int) intersectedWall.getX() / 64];
            }
            Image toDraw;
            if (xOffset > yOffset) {
                toDraw = texture[textureIndex].getSubImage(xOffset, 0, 1, 64);
            } else {
                toDraw = texture[textureIndex].getSubImage(yOffset, 0, 1, 64);
            }

            angleForRayInDegrees += angleBetweenSubsequentRays;
            correctedDistance = rayDistanceToWall.get(col) * (float) cos(toRadians(anglesOfRaysInDegrees.get(col)));
            projectedSliceHeight = wallHeight / correctedDistance * (float) distanceBetweenPlayerAndProjectionPlane;
            Line tmpRay = verticalRays.get(col);
            tmpRay.set(col, centerOfProjectionPlane.getY() - projectedSliceHeight / 2, col, centerOfProjectionPlane.getY() + projectedSliceHeight / 2);
            pixelPositionUnderWall.set(col, (int) (tmpRay.getY2()));
            toDraw.draw(col, centerOfProjectionPlane.getY() - projectedSliceHeight / 2, 1, projectedSliceHeight);
        }
    }


    private void floorRayCast(Graphics graphics) {


        int pixelPosition;
        float straightDistanceFromPlayerToFloorPoint;
        float rayAngleValue;
        float angleRelativeToTheViewingAngleOfThePlayer;
        float actualDistanceFromPlayerToFloorPoint;
        float texX;
        float texY;
        int texXOffset;
        int texYOffset;


        for (int col = 0; col < projectionPlaneWidth; col++) {


            pixelPosition = pixelPositionUnderWall.get(col);
            if (pixelPositionUnderWall.get(col) < projectionPlaneHeight) {

                for (int i = pixelPosition; i < projectionPlaneHeight; i++) {

                    straightDistanceFromPlayerToFloorPoint = (playerHeight * distanceBetweenPlayerAndProjectionPlane) / (i - centerOfProjectionPlane.getY());
                    rayAngleValue = playerDirectionInDegrees + anglesOfRaysInDegrees.get(col);
                    angleRelativeToTheViewingAngleOfThePlayer = playerDirectionInDegrees - rayAngleValue;
                    actualDistanceFromPlayerToFloorPoint = straightDistanceFromPlayerToFloorPoint / (float) cos(toRadians(angleRelativeToTheViewingAngleOfThePlayer));

                    texX = playerPosX + (actualDistanceFromPlayerToFloorPoint * (float) cos(toRadians(rayAngleValue)));
                    texY = playerPosY + (actualDistanceFromPlayerToFloorPoint * (float) sin(toRadians(rayAngleValue)));

                    texXOffset = (int) texX % 64;
                    texYOffset = (int) texY % 64;

                    graphics.setColor(floorTextureArray[texXOffset][texYOffset]);
                    graphics.fillRect(col, i, 1, 1);

                }
            }
        }

    }

    private void renderMap(Graphics graphics) {
        for (Rectangle wall : walls) {
            graphics.setColor(Color.yellow);
            graphics.draw(new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight()));
        }

    }


}
