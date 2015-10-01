public class FastCompetition<E> implements Competition<E> {
	private int size = 0;
	private DoubleNode<E> Head;
	private final int storageSize;

	private int index_Counter = 0;
	private E elementAtIndex = null;
	private Object[] sortedArray;
	//private int sortedSize = 0;

	public FastCompetition(int i) {
		storageSize = i;
	}

	@Override
	public boolean add(E e) {
		if(size<this.storageSize)
		{
		DoubleNode<E> copyRef = this.Head;
		DoubleNode<E> newNode = new DoubleNode<E>(e);

		if (Head != null) {
			while (true) {
				if (copyRef.getDataOne().toString().compareTo(e.toString()) <= 0) {
					copyRef.rightChild+=1;
					if (copyRef.getRight() != null) {
						copyRef = copyRef.getRight();
					} else// if(copyRef.getRight()==null)
					{
						copyRef.setRight(newNode);
						// System.out.println(copyRef.getDataOne()+" 's Right node added  "
						// + newNode.getDataOne());
						size++;
						return true;
						// break;
					}
				}

				else {
					copyRef.leftChild+=1;
					if (copyRef.getLeft() != null) {
						copyRef = copyRef.getLeft();
					} else // if(copyRef.getLeft()==null)
					{
						copyRef.setLeft(newNode);
						// System.out.println(copyRef.getDataOne()+" Left node added  "
						// + newNode.getDataOne());
						size++;
						return true;
						// break;
					}
				}
			}
		}

		else// if(Head==null)
		{ // System.out.println("Head node added   " + newNode.getDataOne());
			size++;
			Head = newNode;
			return true;

		}
		}
		 return false;
	}

	public boolean contains(Object o) {
		DoubleNode<E> copyRef = this.Head;
		int compare;
		if (Head == null)
			return false;

		while (true) {
			compare = copyRef.getDataOne().toString().compareTo(o.toString());
			if (compare == 0) {

				return true;
				// break;
			}

			else if (compare < 0) {
				if (copyRef.getRight() == null) {
					return false;
					// System.out.println("From"+copyRef.getDataOne()+"to Going to node"+copyRef.getRight().getDataOne());
				}

				copyRef = copyRef.getRight();

			}

			else {
				if (copyRef.getLeft() == null) {
					// System.out.println("From"+copyRef.getDataOne()+"to Going to node"+copyRef.getLeft().getDataOne());
					return false;

				}
				copyRef = copyRef.getLeft();

			}
		}
	}

	private void nodeRemover(DoubleNode<E> NodeRef) {

		DoubleNode<E> secondCopyRef = NodeRef.getRight();
		DoubleNode<E> tempPRef;
		DoubleNode<E> tempRef;
		if (secondCopyRef.getLeft() != null) {
			while (secondCopyRef.getLeft().getLeft() != null) {
				secondCopyRef = secondCopyRef.getLeft();
			}
			tempPRef = secondCopyRef;
			tempRef = secondCopyRef.getLeft();

			NodeRef.setDataOne(tempRef.getDataOne());
			tempPRef.setLeft(tempRef.getRight());
			tempRef.setRight(null);

		} else if (secondCopyRef.getLeft() == null) {
			NodeRef.setDataOne(secondCopyRef.getDataOne());
			NodeRef.setRight(secondCopyRef.getRight());
			secondCopyRef.setRight(null);
		}
	}

	public boolean remove(Object o) {
		DoubleNode<E> copyRef = this.Head;
		DoubleNode<E> copyPRef = null;
		int compare;
		if (Head == null)
			return false;

		while (true) {
			compare = copyRef.getDataOne().toString().compareTo(o.toString());
			if (compare == 0) {
				if (copyRef == Head) {
					if (copyRef.getLeft() == null && copyRef.getRight() == null) {
						this.Head = null;
					} else if (copyRef.getLeft() == null
							&& copyRef.getRight() != null) {
						this.Head = copyRef.getRight();
						copyRef = null;
					} else if (copyRef.getRight() == null
							&& copyRef.getLeft() != null) {
						this.Head = copyRef.getRight();
						copyRef = null;
					} else
						nodeRemover(copyRef);
					size--;
					return true;
				} else {
					if (copyRef.getLeft() != null && copyRef.getRight() != null) {
						nodeRemover(copyRef);

					} else if (copyPRef.getLeft() == copyRef) {

						if (copyRef.getLeft() == null) {
							copyPRef.setLeft(copyRef.getRight());
							copyRef.setRight(null);
						} else {
							copyPRef.setLeft(copyRef.getLeft());
							copyRef.setLeft(null);
						}
					} else if ((copyPRef.getRight() == copyRef)) {
						if (copyRef.getLeft() == null) {
							copyPRef.setRight(copyRef.getRight());
							copyRef.setRight(null);
						} else {
							copyPRef.setRight(copyRef.getLeft());
							copyRef.setLeft(null);
						}
					}
					size--;
					return true;
				}
			} else if (compare < 0) {

				if (copyRef.getRight() == null)
					return false;
				copyPRef = copyRef;
				copyRef = copyRef.getRight();

			}

			else {
				if (copyRef.getLeft() != null) {
					copyPRef = copyRef;
					copyRef = copyRef.getLeft();
				} else
					return false;
			}
		}

	}


	@SuppressWarnings("unchecked")
	public E elementAt(int index) {
		/*
		sorting(Head, index);
		
		return this.elementAtIndex;
		*/
/*		DoubleNode<E> node;
		node=Head;
		//index+=1;
		//int height=node.leftChild;
		while(node.leftChild!=index)
		{
		
		if(index>node.leftChild)
		{
			index=index-node.leftChild-1;
			node=node.getRight();
		}
		else
		{
			node=node.getLeft();
			//height=height-1-node.rightChild;
		}
		}
		return node.getDataOne();
		
		*/
		return (E)sortedArray[index];
	}

	@Override
	public Competition<E> sort() {
		// TODO Auto-generated method stub
		DoubleNode<E> copy = Head;
		// SortedArray= new String[size];
		

		// System.out.println(index_Counter+"index counter");
		index_Counter = 0;
		sortedArray= new Object[size];
		sorting(copy, -1);
		return this;
	}

	private void sorting(DoubleNode<E> node, int limit) {
		if (elementAtIndex != null)
			return;
		if (node == null) {
			return;
		} else {
			sorting(node.getLeft(), limit);
			if (limit == -1) {
				//addElementtoSortedLinkList(node.getDataOne());
				sortedArray[index_Counter]=node.getDataOne();
				index_Counter++;
			}
			
		/*	if (index_Counter++ == limit) {
				elementAtIndex = node.getDataOne();
				return;
			}*/
		/*	if (elementAtIndex != null)
				return;*/
			sorting(node.getRight(), limit);

		}
	}

	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	/*private void addElementtoSortedLinkList(E obj) {
		DoubleNode<E> newNode = new DoubleNode<E>(obj);
		if (SortedLinkListStart == null) {

			SortedLinkListEnd = newNode;
			SortedLinkListStart = SortedLinkListEnd;
		} else
		{
			SortedLinkListEnd.setRight(newNode);
			SortedLinkListEnd = SortedLinkListEnd.getRight();
		}
		sortedSize++;

	}*/

}
