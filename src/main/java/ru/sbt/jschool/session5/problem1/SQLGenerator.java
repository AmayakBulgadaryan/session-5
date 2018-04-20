package ru.sbt.jschool.session5.problem1;



import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;


/**
 */
public class SQLGenerator {
    public static void main(String[] args) { }

    private String checkNameForInsert(Field field) {
        Annotation annotation;
        if ((annotation = field.getDeclaredAnnotation(PrimaryKey.class))!=null){
            PrimaryKey pk = (PrimaryKey) annotation;
            if (!pk.name().equals("")){
                return pk.name().toLowerCase();
            }
            return field.getName().toLowerCase();
        }

        if ((annotation = field.getDeclaredAnnotation(Column.class))!=null){
            Column column = (Column) annotation;
            if (!column.name().equals("")){
                return column.name().toLowerCase();
            }
            return field.getName().toLowerCase();
        }
        return "";
    }
    private void checkNameForUDS(Field[] fields, ArrayList<String> primaryKeys, ArrayList<String> columns){

        Annotation annotation;
        for (Field field: fields) {
            if ((annotation = field.getDeclaredAnnotation(PrimaryKey.class)) != null) {
                PrimaryKey pk = (PrimaryKey) annotation;
                if (!pk.name().equals("")) {
                    primaryKeys.add(pk.name().toLowerCase());
                }
                else
                primaryKeys.add(field.getName().toLowerCase());
            }

            if ((annotation = field.getDeclaredAnnotation(Column.class)) != null) {
                Column column = (Column) annotation;
                if (!column.name().equals("")) {
                    columns.add(column.name().toLowerCase());
                }
                else
                columns.add(field.getName().toLowerCase());
            }
        }

    }

    public <T> String insert(Class<T> clazz) {

        Field[] fields = clazz.getDeclaredFields();

        StringBuilder sb = new StringBuilder();

        Annotation an = clazz.getAnnotation(Table.class);
        Table table = (Table) an;

        sb.append("INSERT INTO ").append(table.name()).append("(");
        if (fields[0].getDeclaredAnnotation(Column.class)!=null||fields[0].getDeclaredAnnotation(PrimaryKey.class)!=null)
            sb.append(checkNameForInsert(fields[0]));
        for (int i = 1; i < fields.length; i++) {
            if (fields[i].getDeclaredAnnotation(Column.class)!=null||fields[i].getDeclaredAnnotation(PrimaryKey.class)!=null)
            sb.append(", ").append(checkNameForInsert(fields[i]));
        }
        sb.append(") VALUES (?, ?, ?, ?, ?)");

        return sb.toString();
    }

    public <T> String update(Class<T> clazz) {
        ArrayList<String> columns = new ArrayList<>();
        ArrayList<String> primaryKeys = new ArrayList<>();

        Field[] fields = clazz.getDeclaredFields();

        checkNameForUDS(fields, primaryKeys, columns);

        StringBuilder sb = new StringBuilder();

        Annotation an = clazz.getAnnotation(Table.class);
        Table table = (Table) an;

        sb.append("UPDATE ").append(table.name()).append(" SET ").append(columns.get(0)).append(" = ?");
        for (int i = 1; i < columns.size(); i++) {
            sb.append(", ").append(columns.get(i)).append(" = ?");
        }
        sb.append(" WHERE ").append(primaryKeys.get(0)).append(" = ?");
        for (int i = 1; i < primaryKeys.size(); i++) {
            sb.append(" AND ").append(primaryKeys.get(i)).append(" = ?");
        }

        return sb.toString();
    }

    public <T> String delete(Class<T> clazz) {
        ArrayList<String> columns = new ArrayList<>();
        ArrayList<String> primaryKeys = new ArrayList<>();

        Field[] fields = clazz.getDeclaredFields();

        checkNameForUDS(fields, primaryKeys, columns);

        StringBuilder sb = new StringBuilder();

        Annotation an = clazz.getAnnotation(Table.class);
        Table table = (Table) an;

        sb.append("DELETE FROM ").append(table.name()).append(" WHERE ").append(primaryKeys.get(0)).append(" = ?");
        for (int i = 1; i < primaryKeys.size(); i++) {
            sb.append(" AND ").append(primaryKeys.get(i)).append(" = ?");
        }
        return sb.toString();
    }

    public <T> String select(Class<T> clazz) {
        ArrayList<String> columns = new ArrayList<>();
        ArrayList<String> primaryKeys = new ArrayList<>();

        Field[] fields = clazz.getDeclaredFields();

        checkNameForUDS(fields, primaryKeys, columns);

        StringBuilder sb = new StringBuilder();

        Annotation an = clazz.getAnnotation(Table.class);
        Table table = (Table) an;

        sb.append("SELECT ").append(columns.get(0));
        for (int i = 1; i < columns.size(); i++) {
            sb.append(", ").append(columns.get(i));
        }
        sb.append(" FROM ").append(table.name()).append(" WHERE ").append(primaryKeys.get(0)).append(" = ?");
        for (int i = 1; i < primaryKeys.size(); i++) {
            sb.append(" AND ").append(primaryKeys.get(i)).append(" = ?");
        }

        return sb.toString();
    }
}
