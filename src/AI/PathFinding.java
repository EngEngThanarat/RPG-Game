package AI;

import java.util.ArrayList;

import Entity.Entity;
import main.GamePanel;

public class PathFinding {
    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openlist = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    public PathFinding(GamePanel gp){
        this.gp = gp;
        instantiateNodes();
    }

    public void instantiateNodes(){
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];
        
        int col = 0;
        int row = 0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            node[col][row] = new Node(col,row);
            
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
    }

    public void resetNodes(){
        int col=0;
        int row=0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow){

            // Reset Open, Checked and solid state
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }

        // Reset other settings
        openlist.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNode(int startCol,int startRow, int goalCol, int goalRow, Entity entity){

        resetNodes();

        // set start and Goal node
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openlist.add(currentNode);

        int col=0;
        int row=0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow){

            // SET SOLID NODE
            // CHECK TILES
            int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
            if(gp.tileM.tile[tileNum].collision == true){
                node[col][row].solid = true;
            }

            // SET COST
            getCost(node[col][row]);

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
    }

    public void getCost(Node node){
        // G Cost
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;
        // H Cost
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;
        // F Cost
        node.fCost = node.gCost + node.hCost;
    }

    public boolean search(){

        while(goalReached == false && step < 500){
            
            int col = currentNode.col;
            int row = currentNode.row;

            // Check the current node
            currentNode.checked = true;
            openlist.remove(currentNode);

            // Open the Up node
            if(row - 1 >= 0){
                openNode(node[col][row-1]);
            }
            // Open the Left node
            if(col - 1 >= 0){
                openNode(node[col-1][row]);
            }
            // Open the Down node
            if(row + 1 < gp.maxWorldRow){
                openNode(node[col][row+1]);
            }
            // Open the Right node
            if(col + 1 < gp.maxWorldCol){
                openNode(node[col+1][row]);
            }

            // Find the Best Node
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for(int i=0; i < openlist.size(); i++){
                // Check if this node's F cost is better
                if(openlist.get(i).fCost < bestNodefCost){
                    bestNodeIndex = i;
                    bestNodefCost = openlist.get(i).fCost;
                }
                // if F Cost is equal, check the G Cost
                else if(openlist.get(i).fCost == bestNodefCost){
                    if(openlist.get(i).gCost < openlist.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
            }

            // If there is no node in the openList, end the loop
            if(openlist.size() == 0){
                break;
            }

            // After the loop, OpenList[bestNodeIndex] is the next step (= currentNode)
            currentNode = openlist.get(bestNodeIndex);

            if(currentNode == goalNode){
                goalReached = true;
                trackThaPath();
            }
            step++;
        }
        return goalReached;
    }

    public void openNode(Node node) {
        if(node.open == false && node.checked == false && node.solid == false){
            node.open = true;
            node.parent = currentNode;
            openlist.add(node);
        }
    }

    public void trackThaPath(){
        Node current = goalNode;

        while(current != startNode){
            pathList.add(0,current);
            current = current.parent;
        }
    }
}
