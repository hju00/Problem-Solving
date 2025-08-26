import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class UserSolution {
	
	static Map<Integer, Student> studentDB;
	static TreeSet<Student> gradeGenderStudent[][];
	
	static class Student implements Comparable<Student>	{
		int id;
		int grade;
		int gender;
		int score;
		
		public Student(int id, int grade, int gender, int score) {
			this.id = id;
			this.grade = grade;
			this.gender = gender;
			this.score = score;
		}

		@Override
		public int compareTo(Student o) {
			if(this.score == o.score)
				return Integer.compare(this.id, o.id);
			return Integer.compare(this.score, o.score);
		}
		
	}
	public void init() {
		studentDB = new HashMap<>();
		gradeGenderStudent = new TreeSet[4][2];
		// 0 남자 / 1 여자
		for(int grade = 1; grade <= 3; grade++)
			for(int gender = 0; gender < 2; gender++)
				gradeGenderStudent[grade][gender] = new TreeSet<>();
		
		return;
	}

	public int add(int mId, int mGrade, char mGender[], int mScore) {
		
		if(mGender[0] == 'm')	{
			Student s = new Student(mId, mGrade, 0, mScore);
			studentDB.put(mId, s);
			gradeGenderStudent[mGrade][0].add(s);
			return gradeGenderStudent[mGrade][0].last().id;
		}
		else	{
			Student s = new Student(mId, mGrade, 1, mScore);
			studentDB.put(mId, s);
			gradeGenderStudent[mGrade][1].add(s);
			return gradeGenderStudent[mGrade][1].last().id;
		}
		
	}

	public int remove(int mId) {
		
		if(!studentDB.containsKey(mId))	return 0;
		
		Student toRemove = studentDB.get(mId);
		studentDB.remove(mId);
		
		gradeGenderStudent[toRemove.grade][toRemove.gender].remove(toRemove);
		
		if(!gradeGenderStudent[toRemove.grade][toRemove.gender].isEmpty())
			return gradeGenderStudent[toRemove.grade][toRemove.gender].first().id;
		return 0;
	}

	public int query(int mGradeCnt, int mGrade[], int mGenderCnt, char mGender[][], int mScore) {
	    
	    TreeSet<Student> retStudents = new TreeSet<>();
	    
	    // mGradeCnt 만큼만 유효한 값을 가져와서 사용
	    // foreach 문으로 mGrade 접근 시 런타임에러 발생
	    for (int i = 0; i < mGradeCnt; i++) {
	        int grade = mGrade[i]; 
	        
	        for (int j = 0; j < mGenderCnt; j++) {
	            if (mGender[j][0] == 'm') {
	                Student toCompare = new Student(0, grade, 0, mScore);
	                Student retStudent = gradeGenderStudent[grade][0].ceiling(toCompare);
	                
	                if (retStudent != null) 
	                    retStudents.add(retStudent);
	                
	            } else {
	                Student toCompare = new Student(0, grade, 1, mScore);
	                Student retStudent = gradeGenderStudent[grade][1].ceiling(toCompare);
	                
	                if (retStudent != null) 
	                	retStudents.add(retStudent);
	            }
	        }
	    }
	    
	    if(!retStudents.isEmpty())
	    	return retStudents.first().id;
	    return 0;
	}
}
