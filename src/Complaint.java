import java.io.Serializable;

@SuppressWarnings("serial")
public class Complaint implements Serializable{
    String department;
    int complaintNo;
    String complaint;
    String solution;

    public Complaint(String department, int complaintNo, String complaint, String solution) {
        this.department = department;
        this.complaintNo = complaintNo;
        this.complaint = complaint;
        this.solution = solution;
    }

    @Override
    public String toString(){
        return "Department: " + department + "\n" +
                "Complaint No: " + complaintNo + "\n" +
                "Complaint: " + complaint + "\n" +
                "Solution: " + solution + "\n";
    }
}
