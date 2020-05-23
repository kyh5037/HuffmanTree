package report3;

public class Node {
	int alphaCount;
	char alphabet;
	Node leftChild;
	Node rightChild;
	String sumAlpha;
	
	public Node(char alphabet, int alphaCount) {
		
		this.alphabet = alphabet;
		this.alphaCount = alphaCount;
		leftChild = null;
		rightChild = null;
	}
	
}
