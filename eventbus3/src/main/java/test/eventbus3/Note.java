package test.eventbus3;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;

/**
 * Created by clw on 2017/3/14.
 */
@Entity(indexes = {
        @Index(value = "text, date DESC", unique = true)
})
public class Note {
    @Id
    private int id;
    @NotNull
    private String text;
    private Date date;
@Generated(hash = 1733638822)
public Note(int id, @NotNull String text, Date date) {
    this.id = id;
    this.text = text;
    this.date = date;
}
@Generated(hash = 1272611929)
public Note() {
}
public int getId() {
    return this.id;
}
public void setId(int id) {
    this.id = id;
}
public String getText() {
    return this.text;
}
public void setText(String text) {
    this.text = text;
}
public Date getDate() {
    return this.date;
}
public void setDate(Date date) {
    this.date = date;
}
}
