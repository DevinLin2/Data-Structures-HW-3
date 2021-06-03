import java.util.*;

class DoubleTreePhBook {

  private Node nameRootNode;
  private Node numRootNode;

  private class Node {

    private String name;
    private int number;
    private Node nameTreeParent;
    private Node numTreeParent;
    private Node nameTreeLeft;
    private Node numTreeLeft;
    private Node nameTreeRight;
    private Node numTreeRight;

    public Node(String n, int num) {
      name = n;
      number = num;
      nameTreeParent = null;
      numTreeParent = null;
      nameTreeLeft = null;
      numTreeLeft = null;
      nameTreeRight = null;
      numTreeRight = null;
    }

    /*
    Simple O(1) getter method
    */
    public String getName() {
      return name;
    }

    /*
    Simple O(1) getter method
    */
    public int getNum() {
      return number;
    }

    /*
    Simple O(1) getter method
    */
    public Node getNameTreeParent() {
      return nameTreeParent;
    }

    /*
    Simple O(1) getter method
    */
    public Node getNumTreeParent() {
      return numTreeParent;
    }

    /*
    Simple O(1) getter method
    */
    public Node getNameTreeLeft() {
      return nameTreeLeft;
    }

    /*
    Simple O(1) getter method
    */
    public Node getNumTreeLeft() {
      return numTreeLeft;
    }

    /*
    Simple O(1) getter method
    */
    public Node getNameTreeRight() {
      return nameTreeRight;
    }

    /*
    Simple O(1) getter method
    */
    public Node getNumTreeRight() {
      return numTreeRight;
    }

    /*
    Simple O(1) setter method
    */
    public void setNameTreeParent(Node newNode) {
      nameTreeParent = newNode;
    }

    /*
    Simple O(1) setter method
    */
    public void setNumTreeParent(Node newNode) {
      numTreeParent = newNode;
    }

    /*
    Simple O(1) setter method
    */
    public void setNameTreeLeft(Node newNode) {
      nameTreeLeft = newNode;
    }

    /*
    Simple O(1) setter method
    */
    public void setNumTreeLeft(Node newNode) {
      numTreeLeft = newNode;
    }

    /*
    Simple O(1) setter method
    */
    public void setNameTreeRight(Node newNode) {
      nameTreeRight = newNode;
    }

    /*
    Simple O(1) setter method
    */
    public void setNumTreeRight(Node newNode) {
      numTreeRight = newNode;
    }
  }

  public DoubleTreePhBook() {
    nameRootNode = null;
    numRootNode = null;
  }

  /*
  Worst case asymptotic runtime of O(n) due to non self balancing tree but on average the runtime growth is O(logn)
  */
  public boolean PhBInsert(String name, int num) {
    Node nameTreeParent = null;
    Node numTreeParent = null;
    Node nameTreeCurrent = nameRootNode;
    Node numTreeCurrent = numRootNode;
    if (nameRootNode == null && numRootNode == null) {
      Node newNode = new Node(name, num);
      nameRootNode = newNode;
      numRootNode = newNode;
      return true;
    }
    if (PhBPhoneSearch(num).equals("")) { // number not in phB already
      while (nameTreeCurrent != null) { // find the node where new entry will be inserted and the node's parent
        nameTreeParent = nameTreeCurrent;
        if (name.compareTo(nameTreeCurrent.getName()) > 0) {
          nameTreeCurrent = nameTreeCurrent.getNameTreeRight();
        } else {
          nameTreeCurrent = nameTreeCurrent.getNameTreeLeft();
        }
      }
      while (numTreeCurrent != null) { // find the node where new entry will be inserted and the node's parent
        numTreeParent = numTreeCurrent;
        if (num > numTreeCurrent.getNum()) {
          numTreeCurrent = numTreeCurrent.getNumTreeRight();
        } else {
          numTreeCurrent = numTreeCurrent.getNumTreeLeft();
        }
      }
      if (name.compareTo(nameTreeParent.getName()) > 0) { // inserts the new entry based on binary tree restrictions
        Node newNode = new Node(name, num);
        newNode.setNameTreeParent(nameTreeParent);
        nameTreeParent.setNameTreeRight(newNode);
      } else {
        Node newNode = new Node(name, num);
        newNode.setNameTreeParent(nameTreeParent);
        nameTreeParent.setNameTreeLeft(newNode);
      }
      if (num > numTreeParent.getNum()) {
        Node newNode = new Node(name, num);
        newNode.setNumTreeParent(numTreeParent);
        numTreeParent.setNumTreeRight(newNode);
      } else {
        Node newNode = new Node(name, num);
        newNode.setNumTreeParent(numTreeParent);
        numTreeParent.setNumTreeLeft(newNode);
      }
      return true;
    }
    return false;
  }

  /*
  Worst case asymptotic runtime of O(n) due to non self balancing tree but on average the runtime growth is O(logn)
  */
  public boolean PhBDelete(String name, int num) {
    Node nameTreeParent = null;
    Node numTreeParent = null;
    Node nameTreeCurrent = nameRootNode;
    Node numTreeCurrent = numRootNode;
    while (nameTreeCurrent != null && !nameTreeCurrent.getName().equals(name) && nameTreeCurrent.getNum() != num) { // finds node to be deleted and its parent
      nameTreeParent = nameTreeCurrent;
      if (name.compareTo(nameTreeCurrent.getName()) > 0) {
        nameTreeCurrent = nameTreeCurrent.getNameTreeRight();
      } else {
        nameTreeCurrent = nameTreeCurrent.getNameTreeLeft();
      }
    }
    while (numTreeCurrent != null && numTreeCurrent.getNum() != num) { // finds node to be deleted and its parent
      numTreeParent = numTreeCurrent;
      if (num > numTreeCurrent.getNum()) {
        numTreeCurrent = numTreeCurrent.getNumTreeRight();
      } else {
        numTreeCurrent = numTreeCurrent.getNumTreeLeft();
      }
    }
    if (nameTreeCurrent == null || numTreeCurrent == null) { // deletes node by running helper functions
      return false;
    } else {
      deleteNameNode(nameTreeParent, nameTreeCurrent);
      deleteNumNode(numTreeParent, numTreeCurrent);
      return true;
    }
  }

  /*
  Worst case asymptotic runtime of O(n) due to non self balancing tree but on average the runtime growth is O(logn)
  */
  public LinkedList<Integer> PhBNameSearch(String name) {
    LinkedList<Integer> ans = new LinkedList<Integer>();
    nameSearchHelper(ans, name, nameRootNode);
    return ans;
  }

  /*
  Worst case asymptotic runtime of O(n) due to non self balancing tree but on average the runtime growth is O(logn)
  */
  public String PhBPhoneSearch(int num) {
    Node ans = numSearchHelper(numRootNode, num);
    if (ans == null) {
      return "";
    }
    return ans.getName();
  }

  /*
  Worst case asymptotic runtime of O(n) due to non self balancing tree but on average the runtime growth is O(logn)
  */
  private void nameSearchHelper(LinkedList<Integer> list, String name, Node root) {
    if (root == null) {
      return;
    } else if (root.getName().equals(name)) {
      list.add(root.getNum());
      nameSearchHelper(list, name, root.getNameTreeLeft());
    } else if (name.compareTo(root.getName()) > 0) {
      nameSearchHelper(list, name, root.getNameTreeRight());
    } else {
      nameSearchHelper(list, name, root.getNameTreeLeft());
    }
  }

  /*
  Worst case asymptotic runtime of O(n) due to non self balancing tree but on average the runtime growth is O(logn)
  */
  private Node numSearchHelper(Node root, int num) {
    Node current = numRootNode;
    Node found = null;
    while (current != null & found == null) {
      if (num == current.getNum()) {
        found = current;
      } else if (num > current.getNum()) {
        current = current.getNumTreeRight();
      } else {
        current = current.getNumTreeLeft();
      }
    }
    return found;
  }

  /*
  Worst case asymptotic runtime of O(n) due to non self balancing tree and the extreme case that the
  whole tree has to be parsed through to find the smallest replacement value,
  but on average the runtime growth is O(logn) as there is only one loop that on average parses through half the elements.
  */
  private void deleteNameNode(Node parent, Node toDelete) {
    if (toDelete.getNameTreeLeft() == null || toDelete.getNameTreeRight() == null) { // case where node to delete has less than 2 children
      Node toDeleteChild = null;
      if (toDelete.getNameTreeLeft() != null) {
        toDeleteChild = toDelete.getNameTreeLeft();
      } else {
        toDeleteChild = toDelete.getNameTreeRight();
      }
      if (toDelete == nameRootNode) {
        nameRootNode = toDeleteChild;
      } else if (toDelete.getName().compareTo(parent.getName()) > 0) {
        parent.setNameTreeRight(toDeleteChild);
        toDeleteChild.setNameTreeParent(parent);
      } else {
        parent.setNameTreeLeft(toDeleteChild);
        toDeleteChild.setNameTreeParent(parent);
      }
    } else { // case where node to delete has 2 children
      Node replacementParent = toDelete;
      Node replacement = toDelete.getNameTreeRight();
      while (replacement.getNameTreeLeft() != null) { // find smallest element in right subtree
        replacementParent = replacement;
        replacement = replacement.getNameTreeLeft();
      }
      if (replacement.getNameTreeRight() == null) {
        replacementParent.setNameTreeLeft(null); // deletes reference to replacement from parent
        //moves replacement to the original position of toDelete by changing pointers
        replacement.setNameTreeLeft(toDelete.getNameTreeLeft());
        replacement.setNameTreeRight(toDelete.getNameTreeRight());
        replacement.setNameTreeParent(toDelete.getNameTreeParent());
        replacement.getNameTreeLeft().setNameTreeParent(replacement);
        replacement.getNameTreeRight().setNameTreeParent(replacement);
        if (parent == null) {
          nameRootNode = replacement;
        }
        if (parent != null && parent.getNameTreeLeft().getName().equals(toDelete.getName()) && parent.getNameTreeLeft().getNum() == toDelete.getNum()) {
          parent.setNameTreeLeft(replacement);
        } else if (parent != null){
          parent.setNameTreeRight(replacement);
        }
      } else {
        if (replacementParent.getNameTreeLeft().getName().equals(replacement.getName()) && replacementParent.getNameTreeLeft().getNum() == replacement.getNum()) {
          replacementParent.setNameTreeLeft(replacement.getNameTreeRight());
        } else {
          replacementParent.setNameTreeRight(replacement.getNameTreeRight());
        }
        //moves replacement to the original position of toDelete by changing pointers
        replacement.setNameTreeLeft(toDelete.getNameTreeLeft());
        replacement.setNameTreeRight(toDelete.getNameTreeRight());
        replacement.setNameTreeParent(toDelete.getNameTreeParent());
        replacement.getNameTreeLeft().setNameTreeParent(replacement);
        replacement.getNameTreeRight().setNameTreeParent(replacement);
        if (parent == null) nameRootNode = replacement;
        if (parent != null && parent.getNameTreeLeft().getName().equals(toDelete.getName()) && parent.getNameTreeLeft().getNum() == toDelete.getNum()) {
          parent.setNameTreeLeft(replacement);
        } else if (parent != null){
          parent.setNameTreeRight(replacement);
        }
      }
    }
  }

  /*
  Worst case asymptotic runtime of O(n) due to non self balancing tree and the extreme case that the
  whole tree has to be parsed through to find the smallest replacement value,
  but on average the runtime growth is O(logn) as there is only one loop that on average parses through half the elements.
  */
  private void deleteNumNode(Node parent, Node toDelete) {
    if (toDelete.getNumTreeLeft() == null || toDelete.getNumTreeRight() == null) { // case where node to delete has less than 2 children
      Node toDeleteChild = null;
      if (toDelete.getNumTreeLeft() != null) {
        toDeleteChild = toDelete.getNumTreeLeft();
      } else {
        toDeleteChild = toDelete.getNumTreeRight();
      }
      if (toDelete == numRootNode) {
        numRootNode = toDeleteChild;
      } else if (toDelete.getNum() > parent.getNum()) {
        parent.setNumTreeRight(toDeleteChild);
        toDeleteChild.setNumTreeParent(parent);
      } else {
        parent.setNumTreeLeft(toDeleteChild);
        toDeleteChild.setNumTreeParent(parent);
      }
    } else { // case where node to delete has 2 children
      Node replacementParent = toDelete;
      Node replacement = toDelete.getNumTreeRight();
      while (replacement.getNumTreeLeft() != null) { // find smallest element in right subtree
        replacementParent = replacement;
        replacement = replacement.getNumTreeLeft();
      }
      if (replacement.getNumTreeRight() == null) {
        replacementParent.setNumTreeLeft(null); // deletes reference to replacement from parent
        //moves replacement to the original position of toDelete by changing pointers
        replacement.setNumTreeLeft(toDelete.getNumTreeLeft());
        replacement.setNumTreeRight(toDelete.getNumTreeRight());
        replacement.setNumTreeParent(toDelete.getNumTreeParent());
        replacement.getNumTreeLeft().setNumTreeParent(replacement);
        replacement.getNumTreeRight().setNumTreeParent(replacement);
        if (parent == null) {
          numRootNode = replacement;
        }
        if (parent != null && parent.getNumTreeLeft().getNum() == toDelete.getNum() && parent.getNumTreeLeft().getNum() == toDelete.getNum()) {
          parent.setNumTreeLeft(replacement);
        } else if (parent != null){
          parent.setNumTreeRight(replacement);
        }
      } else {
        if (replacementParent.getNumTreeLeft().getName().equals(replacement.getName()) && replacementParent.getNumTreeLeft().getNum() == replacement.getNum()) {
          replacementParent.setNumTreeLeft(replacement.getNumTreeRight());
        } else {
          replacementParent.setNumTreeRight(replacement.getNumTreeRight());
        }
        //moves replacement to the original position of toDelete by changing pointers
        replacement.setNumTreeLeft(toDelete.getNumTreeLeft());
        replacement.setNumTreeRight(toDelete.getNumTreeRight());
        replacement.setNumTreeParent(toDelete.getNumTreeParent());
        replacement.getNumTreeLeft().setNumTreeParent(replacement);
        replacement.getNumTreeRight().setNumTreeParent(replacement);
        if (parent == null) numRootNode = replacement;
        if (parent != null && parent.getNumTreeLeft().getName().equals(toDelete.getName()) && parent.getNumTreeLeft().getNum() == toDelete.getNum()) {
          parent.setNumTreeLeft(replacement);
        } else if (parent != null){
          parent.setNumTreeRight(replacement);
        }
      }
    }
  }

  /*
  Printing a tree is a O(n) algorithm 
  */
  public void printPhoneBookTrees() {
    // Preorder traversal to print out the phonebook
    System.out.println("Num Tree:");
    numPreorderPrint(numRootNode);
    System.out.println("\n");
    System.out.println("Name tree: ");
    namePreorderPrint(nameRootNode);
  }

  private void namePreorderPrint(Node root) {
    if (root == null) return;
    System.out.print(root.getName() + ": " + root.getNum() + " ");
    namePreorderPrint(root.getNameTreeLeft());
    namePreorderPrint(root.getNameTreeRight());
  }

  private void numPreorderPrint(Node root) {
    if (root == null) return;
    System.out.print(root.getNum() + ": " + root.getName() + " ");
    numPreorderPrint(root.getNumTreeLeft());
    numPreorderPrint(root.getNumTreeRight());
  }
}
