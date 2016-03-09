package sort;

public class driver {

	public static void main(String[] args) {
		Integer[] a = {17,10,2,20,18,4,1,11,0,19,14,5,3,12,7,6,9,8,13,16,15};
		
		InsertionSort.sort(a);
		for(int i=0;i<a.length;i++){
		System.out.print(a[i]);}
		
		System.out.print("\n");
		
		KnuthShuffle.shuffle(a);
		for(int i=0;i<a.length;i++){
		System.out.print(a[i]);}
		
		System.out.print("\n");
		
		MergeSort.sort(a);
		for(int i=0;i<a.length;i++){
		System.out.print(a[i]);}
		
		System.out.print("\n");
		
		//System.out.println(selection.QuickSelect.select(a, 1));
		
		KnuthShuffle.shuffle(a);
		for(int i=0;i<a.length;i++){
		System.out.print(a[i]);}
		
		System.out.print("\n");
		
		QuickSort.threeWaySort(a);
		for(int i=0;i<a.length;i++){
		System.out.print(a[i]);}
		
		System.out.print("\n");
		
		KnuthShuffle.shuffle(a);
		for(int i=0;i<a.length;i++){
		System.out.print(a[i]);}
		
		System.out.print("\n");
		
		HeapSort.sort(a);
		for(int i=0;i<a.length;i++){
		System.out.print(a[i]);}
	}

}
