package oraclejavasoapkubernetes;
 
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
 
@Component
public class StudentRepository {
    private static final Map<String, Student> students = new HashMap<>();
 
    @PostConstruct
    public void initData() {
         
        Student student = new Student("Cristiano", "Hoshikawa");
        students.put(student.getName(), student);
         
        student = new Student("Kajal", "Huwaey");
        students.put(student.getName(), student);
         
    }
 
    public Student findStudent(String name) {
        Assert.notNull(name, "The Student's name must not be null");
        return students.get(name);
    }
}