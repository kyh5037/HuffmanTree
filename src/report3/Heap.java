package report3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Heap {
	
	List<Node> heap;

	
	public Heap() {
		
		heap = new ArrayList<Node>();
		heap.add(null); // 배열[0]은 NULL 값 저장

	}
	
	public void insert(Node node) { // 최소 힙 삽입 함수
		
		heap.add(node); // 최소 힙에 node 추가
		
		int child = heap.size()-1;
		int parent = child / 2;
		
		while(parent >= 1 && heap.get(child).alphaCount < heap.get(parent).alphaCount) { // 자식이 부모보다 클 경우 종료
			
			Node temp = heap.get(child); 
            heap.set(child, heap.get(parent));
            heap.set(parent, temp);
			
			child = parent;
			parent = child/2;
		}
	}

	public Node sortTree() { // 최소 힙 재 정렬 함수
		
		if(heap.size() <= 1) return null; // 최소 힙이 비어있으면 null 반환
		
		Node root = heap.get(1); // 최소 힙 루트 노드
		
		heap.set(1, heap.get(heap.size() - 1)); // 그 다음 노드 저장 
		heap.remove(heap.size() - 1);
		
		int parent = 1;
		int leftChild = parent * 2;
		int rightChild = parent * 2 + 1;
		
		while(leftChild <= heap.size() - 1) { 
			
			int selectChild; // 선택 자식 변수

			if(rightChild > heap.size() - 1) { // 자식들이 없을 경우 종료 
				
				if (heap.get(leftChild).alphaCount >= heap.get(parent).alphaCount) 
					break;
				
				selectChild = leftChild; // 왼쪽 자식이 있을 경우 왼쪽 자식 선택
				
			} else {
				
				if (heap.get(leftChild).alphaCount >= heap.get(parent).alphaCount
						&& heap.get(rightChild).alphaCount >= heap.get(parent).alphaCount) // 자식들이 부모보다 클 경우 종료
					break;
				
				if (heap.get(leftChild).alphaCount > heap.get(rightChild).alphaCount) // 왼쪽 자식이 오른쪽 자식보다 클 경우 오른쪽 자식 선택
					selectChild = rightChild;
				else // 오른쪽 자식이 왼쪽 자식보다 클 경우 왼쪽 자식 선택
					selectChild = leftChild;
				
			}
			
			if(heap.get(parent).alphaCount <= heap.get(selectChild).alphaCount) break;
			
			Node temp = heap.get(parent); // 부모와 자식 swap
            heap.set(parent, heap.get(selectChild));
            heap.set(selectChild, temp);
            parent = selectChild;
			
			parent = selectChild;
			leftChild = parent * 2;
			rightChild = parent * 2 + 1;
		}
		return root;
	}

}
