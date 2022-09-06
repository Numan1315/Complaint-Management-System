import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

public class Model {
	private String fileName;
	int totalComps;
	private List<Complaint> complaintList;

	public Model(String fileName) {
		this.fileName = fileName;
		initList();
		this.totalComps = complaintList.size();
	}

	private void initList() {
		complaintList = new ArrayList<>();
		File f = new File(fileName);
		if (f.exists()) {
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(new FileInputStream(fileName));
				while (true) {
					complaintList.add((Complaint) ois.readObject());
				}
			} catch (EOFException eof) {
				if (ois != null) {
					try {
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void addComp(Complaint comp) {
		complaintList.add(comp);
	}

	public void updateTotalComps(){
		this.totalComps++;
	}
	public void addSoln(int complaintNo, String solution) throws Exception {
		addSoln(complaintNo, solution, false);
	}

	public void overwriteSoln(int complaintNo, String solution) {
		try {
			addSoln(complaintNo, solution, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addSoln(int complaintNo, String solution, boolean overWrite) throws Exception {
		Complaint comp = getComp(complaintNo);
		if (comp.solution.isEmpty() || overWrite) {
			Complaint newComp = new Complaint(comp.department, comp.complaintNo, comp.complaint, solution);
			remove(comp);
			addComp(newComp);
		} else if (!comp.solution.isEmpty()) {
			throw new Exception("Solution Already Exists");
		}
	}

	public String getSoln(int complaintNo) {
		Complaint comp = getComp(complaintNo);
		if (comp != null) {
			return comp.solution;
		}
		return null;
	}

	public boolean findComp(int complaintNo) {
		return getComp(complaintNo) != null;
	}

	private Complaint getComp(int complaintNo) {
		for (Complaint comp : complaintList) {
			if (comp.complaintNo == complaintNo) {
				return comp;
			}
		}
		return null;
	}

	private void remove(Complaint compTbr) {
		complaintList.remove(compTbr);
	}

	public JTable returnTable() {
		JTable table;
		Object columnNames[] = { "C.No.", "Department", "Complaint", "Solution" };
		Object rowData[][] = new Object[totalComps][columnNames.length];
		int i = 0;
		for (Complaint comp : complaintList) {
			rowData[i][0] = comp.complaintNo;
			rowData[i][1] = comp.department;
			rowData[i][2] = comp.complaint;
			rowData[i][3] = comp.solution;
			++i;
		}
		table = new JTable(rowData, columnNames);
		return table;
	}

	public void exit() {
		try {
			FileWriter fw = new FileWriter(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
			oos.flush();
			for (Complaint comp : complaintList) {
				oos.writeObject(comp);
				oos.flush();
			}
			oos.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
}
