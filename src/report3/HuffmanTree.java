package report3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HuffmanTree {
	public static HashMap<Character, Integer> map = new HashMap<Character, Integer>(); // 문자, 빈도 수를 저장할 해시함수 생성 
	
	void FileRead() {
		try {
			File file = new File("C://myJSP/workspace/example2/src/test.txt"); // 파일을 불러오고 버퍼에 저장
			FileReader FR = new FileReader(file);
			BufferedReader BR = new BufferedReader(FR);
			String string;
			
			while((string = BR.readLine()) != null){ // 버퍼에서 읽은 값들을 String 변수에 저장
				for(int i = 0; i < string.length(); i++) {
					char alphabet = string.charAt(i);
					if(map.containsKey(alphabet)) // alphabet이 alphaCount에 포함되어 있다?
						map.put(alphabet, map.get(alphabet)+1); //value 값에 +1
					else
						map.put(alphabet, 1); // 아니면 추가하고 1로 Count 값 지정
				}
			}
			FR.close();
		}
		catch(FileNotFoundException e){
			System.out.println(e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	Node Huffman() {
		Node huffParent = null;
		Heap min_Heap = new Heap();
		
		Set set = map.keySet(); 
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) { // 해시 함수에 저장된 key, value값을 최소 힙에 저장
			char key = (char) iterator.next();
			Node node = new Node(key, map.get(key));
			min_Heap.insert(node);
		}
		
		while(true) {
			
			Node lChild = min_Heap.sortTree(); // 재 정렬후 2개의 value 값을 합친다.
			Node rChild = min_Heap.sortTree();
			
			if(lChild == null || rChild == null)
				break;
			
			char huffAlpha = '-';
			int huffCount = lChild.alphaCount + rChild.alphaCount; 
			String huffSum = lChild.alphabet + "+" + rChild.alphabet;
			
			huffParent = new Node(huffAlpha ,huffCount); // 더한 값의 Node를 생성
			huffParent.sumAlpha = huffSum; // 어떤 값 들이 더해졌는지 저장
			
			huffParent.leftChild = lChild; // HuffmanTree Node의 자식은 더하기 전의 Node들이다.
			huffParent.rightChild = rChild;
			
			min_Heap.insert(huffParent); // HuffmanTree Node를 다시 힙에 insert
		}
		
		return huffParent;
	}
	
	void huffmanCode(Node node, String code) { // 순환 함수를 통한 호프만 코드 출력 함수
		
		String leftCode = code;
		String rightCode = code;
		
		if(node.leftChild != null) { // 왼쪽 자식은 0을 추가
			rightCode += "0";
			huffmanCode(node.leftChild, rightCode);
		}
		
		if(node.rightChild != null) { // 오른쪽 자식은 1을 추가
			leftCode += "1";
			huffmanCode(node.rightChild, leftCode);
		}

		if(node.leftChild == null && node.rightChild == null) {
			System.out.println("문자: " + node.alphabet + " 빈도수: " + node.alphaCount + " 코드: " + code);
		}
			
	}
}
