package cn.raocloud.interview.algorithm.other;

/**
 二叉查找树特点：
    1、若它的左子树不为空，则左子树上所有的节点值都小于它的根节点值
    2、若它的右子树不为空，则右子树上所有的节点值都大于它的根节点值
    3、它的左右子树也可以充当二叉查找树
 二叉查找树优缺点：
    1、查找性能比较好，时间复杂度为O(log(n))
    2、在某些极端的情况下会蜕变成类似连表结构，时间复杂度为O(n)
 平衡二叉查找树特点：
    1、具备二叉查找树的所有特点
    2、每个节点的左子树和右子树的高度差至多等于1，通过节点的左右旋进行平衡
 平衡二叉查找树使用场景：
    1、AVL树是严格的平衡二叉查找树，不管是执行插入或者删除操作，只要不能满足上面的条件必须通过旋转来达到平衡，
    而旋转操作是非常耗时的操作，因为AVL树适用于插入或者删除较少，查询较多的场景
    2、在查找频率比较高且插入或者删除频次比较少的情况，AVL树的性能比红黑树脚好点
 红黑树特点：
    1、每个节点或者是黑色，或者是红色
    2、根节点是黑色
    3、每个叶子节点是黑色
    4、如果一个节点是红色，那么它的子节点必须是黑色
    5、从任意节点到叶子节点，经过的黑色节点数是一样的
 *
 * @param <T>
 */
public class AvlTree<T extends Comparable<T>> {

    public int height(AvlNode<T> avlNode) {
        if (avlNode == null) {
            return -1;
        } else {
            return avlNode.getHeight();
        }
    }

    public AvlNode<T> leftRotate(AvlNode<T> avlNode) {
        AvlNode<T> tempNode = avlNode.getRightNode();
        avlNode.setRightNode(tempNode.getLeftNode());
        tempNode.setLeftNode(avlNode);

        avlNode.setHeight(Math.max(height(avlNode.getLeftNode()), height(avlNode.getRightNode())) + 1);
        tempNode.setHeight(Math.max(height(tempNode.getLeftNode()), height(tempNode.getRightNode())) + 1);
        return tempNode;

    }

    public AvlNode<T> rightRotate(AvlNode<T> avlNode) {
        AvlNode<T> tempNode = avlNode.getLeftNode();
        avlNode.setLeftNode(tempNode.getRightNode());
        tempNode.setRightNode(avlNode);

        avlNode.setHeight(Math.max(height(avlNode.getLeftNode()), height(avlNode.getRightNode())) + 1);
        tempNode.setHeight(Math.max(height(tempNode.getLeftNode()), height(tempNode.getRightNode())) + 1);
        return tempNode;
    }

    public AvlNode<T> leftRightRotate(AvlNode<T> avlNode) {
        avlNode.setLeftNode(leftRotate(avlNode.getLeftNode()));
        return rightRotate(avlNode);
    }

    public AvlNode<T> rightLeftRotate(AvlNode<T> avlNode) {
        avlNode.setRightNode(rightRotate(avlNode.getRightNode()));
        return leftRotate(avlNode);
    }

    public void print(AvlNode<T> rootNode) {
        if (rootNode == null) { return; }
        print(rootNode.getLeftNode());
        System.out.println("height: " + rootNode.getHeight() + ", data: " + rootNode.getData());
        print(rootNode.getRightNode());
    }

    public AvlNode<T> insert(T data, AvlNode<T> avlNode) {
        if (avlNode == null) {
            avlNode = new AvlNode<>();
            avlNode.setData(data);
        } else if (data.compareTo(avlNode.getData()) < 0) {
            avlNode.setLeftNode(insert(data, avlNode.getLeftNode()));
            if (height(avlNode.getLeftNode()) - height(avlNode.getRightNode()) > 1) {
                if (data.compareTo(avlNode.getLeftNode().getData()) < 0) {
                    avlNode = rightRotate(avlNode);
                } else {
                    avlNode = leftRightRotate(avlNode);
                }
            }
        } else if (data.compareTo(avlNode.getData()) > 0) {
            avlNode.setRightNode(insert(data, avlNode.getRightNode()));
            if (height(avlNode.getRightNode()) - height(avlNode.getLeftNode()) > 1) {
                if (data.compareTo(avlNode.getRightNode().getData()) > 0) {
                    avlNode = leftRotate(avlNode);
                } else {
                    avlNode = rightLeftRotate(avlNode);
                }
            }
        }
        avlNode.setHeight(Math.max(height(avlNode.getLeftNode()), height(avlNode.getRightNode())) + 1);
        return avlNode;
    }

    public static void main(String[] args) {
        int[] insertArray = {1, 4, 3, 2, 5, 6, 10, 9, 7};
        AvlTree<Integer> avlTree = new AvlTree<>();
        AvlNode<Integer> rootNode = avlTree.insert(insertArray[0], null);
        for (int index = 1; index < insertArray.length; index++) {
            rootNode = avlTree.insert(insertArray[index], rootNode);
        }
        avlTree.print(rootNode);
    }

    public static class AvlNode<T> {
        private T data;
        private AvlNode<T> leftNode;
        private AvlNode<T> rightNode;
        private int height;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public AvlNode<T> getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(AvlNode<T> leftNode) {
            this.leftNode = leftNode;
        }

        public AvlNode<T> getRightNode() {
            return rightNode;
        }

        public void setRightNode(AvlNode<T> rightNode) {
            this.rightNode = rightNode;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}