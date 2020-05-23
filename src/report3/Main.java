package report3;

public class Main {
	public static void main(String[] args) {
		HuffmanTree huf = new HuffmanTree();
		String str ="";
		
		huf.FileRead();
		Node root = huf.Huffman();
		System.out.println("---- Huffman Algorithm ----");
		huf.huffmanCode(root, str);
	}
}
