import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: joshessex
 * Date: 11/13/13
 * Time: 3:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class BinarizedTree {

    static String[] WORDS = new String[] {"does", "it", "have", "to", "be", "awful?"};

    public static void main(String[] args) {
        setup(WORDS);
    }

    static class node {
        node left_child;
        node right_child;
        String content;

        node(node left, node right, String content) {
            this.left_child = left;
            this.right_child = right;
            this.content = content;
        }

        node(String content) {
            this.left_child = null;
            this.right_child = null;
            this.content = content;
        }
    }

    static void setup(String[] words) {
        Stack<node> miniTrees = new Stack<node>();
        node left = new node(null, null, words[0]);
        node right = new node(null, null, words[1]);
        node tree = new node(left, right, null);
        miniTrees.push(tree);

        String[] newWords = new String[words.length - 2];
        for (int i = 2; i < words.length; i++) {
            newWords[i - 2] = words[i];
        }

        node finalTree = binarizeTree(newWords, miniTrees);
        print(finalTree);
    }

    static node binarizeTree(String[] words, Stack<node> miniTrees) {
        for(String word : words) {
            node currTree = miniTrees.pop();
            node newNode = new node(word);
            if(currTree.right_child == null) {
                currTree.right_child = newNode;
                if (!miniTrees.isEmpty()) {
                    node left = miniTrees.pop();
                    node newParent = new node(left, currTree, null);
                    miniTrees.push(newParent);
                }
                else {
                    miniTrees.push(currTree);
                }
            } else {
                node newParent = new node(newNode, null, null);
                miniTrees.push(currTree);
                miniTrees.push(newParent);
            }
        }
        // two cases
        node topTree = miniTrees.pop();
        node secondTree;
        node finalTree;

        if(topTree.right_child == null) {
            //bad case
            secondTree = miniTrees.pop();
            finalTree = new node(secondTree, topTree.left_child, null);
            miniTrees.push(finalTree);
        } else {
            //good case
            finalTree = topTree;
            miniTrees.push(finalTree);
        }

        return finalTree;

    }

    static void print(node tree) {
        if (tree != null) {
            System.out.print('(');
            System.out.println(tree.content);
            print(tree.left_child);
            print(tree.right_child);
            System.out.println(')');
        }
    }

    static void printContent(node node) {
        System.out.println("PUSHING THIS: " + node.content);
    }
}
