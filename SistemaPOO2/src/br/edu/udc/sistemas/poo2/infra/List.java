package br.edu.udc.sistemas.poo2.infra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class List extends MyObject {

	private No head;
	private int size;

	public List() {
		this.head = null;
		this.size = 0;
	}

	public int getSize() {
		return this.size;
	}

	public void add(MyObject obj) {
		if (obj != null) {
			No newNo = new No();
			newNo.setData(obj.clone());

			if (this.head == null) {
				this.head = newNo;
			} else {
				No pAux = this.head;
				while (pAux.getNext() != null) {
					pAux = pAux.getNext();
				}
				pAux.setNext(newNo);
			}
			this.size++;
		}
	}

	public boolean remove(int id) {
		MyObject obj = new MyObject();
		obj.setId(id);
		return this.remove(obj);
	}

	public boolean remove(MyObject obj) {
		No pAnt = this.head;
		No pProx = this.head;

		while ((pProx != null) && (pProx.getData().getId() != obj.getId())) {
			pAnt = pProx;
			pProx = pProx.getNext();
		}

		if (pProx != null) {
			if (pProx == this.head) {
				this.head = this.head.getNext();
			} else {
				pAnt.setNext(pProx.getNext());
			}
			pProx.setNext(null);
			this.size--;
			return true;
		}
		return false;
	}

	public List find(MyObject obj) {
		No pAux = this.head;
		List filteredList = new List();
		while (pAux != null) {
			if ((obj == null) || (pAux.getData().equals(obj))) {
				filteredList.add(pAux.getData().clone());
			}
			pAux = pAux.getNext();
		}
		return filteredList;
	}

	public MyObject findById(int id) {
		MyObject obj = new MyObject();
		obj.setId(id);
		MyObject result = this.findById(obj);

		return result;
	}

	public MyObject findById(MyObject obj) {
		if (obj != null) {
			No pAux = this.head;
			while ((pAux != null) && (pAux.getData().getId() != obj.getId())) {
				pAux = pAux.getNext();
			}
			if (pAux != null) {
				return pAux.getData().clone();
			}
		}
		return null;
	}

	public MyObject[] toArray() {
		MyObject vector[] = new MyObject[this.size];
		int i = 0;
		No pAux = this.head;
		while (pAux != null) {
			vector[i] = pAux.getData().clone();
			pAux = pAux.getNext();
			i++;
		}
		return vector;
	}

	public void loadFromFile(String fileName, MyObject baseObject) throws Exception {
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String str = br.readLine(); //Come o primeiro enter
			str = br.readLine();
			while (str != null) {
				MyObject obj = baseObject.getClass().newInstance();
				obj.setString(str);
				this.add(obj);
				str = br.readLine();
			}
		} catch (IOException e) {
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}

	public void saveToFile(String fileName) throws Exception {
		File f = new File(fileName);
		FileWriter fw = null;
		try {
			fw = new FileWriter(f);
			No pAux = this.head;
			while (pAux != null) {
				fw.write("\n" + pAux.getData().getString());
				pAux = pAux.getNext();
			}
		} catch (IOException e) {
			throw e;
		} finally {
			fw.close();
		}
	}

}
