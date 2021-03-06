package dao;

import entity.Document;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentDAO extends SuperDAO implements DaoArayuz<Document> {

    PreparedStatement pst;
    ResultSet rs;

    @Override
    public boolean insert(Document document) {

        try {
            pst = this.getConnection().prepareStatement("insert into document(name,path,type) values(?,?,?)");
            pst.setString(1, document.getFileName());
            pst.setString(2, document.getFilePath());
            pst.setString(3, document.getFileType());

            return pst.executeUpdate() != 0;

        } catch (SQLException ex) {
            System.out.println(" DocumentDAO HATA(Create): " + ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Document document) {

        try {
            pst = this.getConnection().prepareStatement("delete from document where id=?");
            pst.setInt(1, document.getId());
            return pst.executeUpdate() != 0;

        } catch (SQLException ex) {
            System.out.println(" DocumentDAO HATA(Delete): " + ex.getMessage());
        }
        return false;
    }

    @Override
    public int count() {
        int count = 0;

        try {
            pst = this.getConnection().prepareStatement("select count(id) as document_count from document");
            rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("document_count");
        } catch (SQLException ex) {
            System.out.println("DocumentDAO HATA(Count): " + ex.getMessage());
        }
        return count;
    }

    @Override
    public List<Document> findAll(String deger, int page, int pageSize) {
        List<Document> dlist = new ArrayList();
        int start = (page - 1) * pageSize;
        try {
            pst = this.getConnection().prepareStatement("select * from document where name ilike ? or type ilike ? order by id asc limit ? offset ?");
            pst.setString(1, "%" + deger + "%");
            pst.setString(2, "%" + deger + "%");
            pst.setInt(3, pageSize);
            pst.setInt(4, start);
            rs = pst.executeQuery();

            while (rs.next()) {
                Document temp = new Document(rs.getInt("id"), rs.getString("name"), rs.getString("path"), rs.getString("type"));
                dlist.add(temp);
            }
        } catch (SQLException ex) {
            System.out.println("DocumentDAO HATA(FindAll):" + ex.getMessage());
        }
        return dlist;
    }

    @Override
    public Document find(int id) {
        Document d = null;
        try {
            pst = this.getConnection().prepareStatement("select * from document where id=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            rs.next();
            d = new Document(rs.getInt("id"), rs.getString("name"), rs.getString("path"), rs.getString("type"));

        } catch (SQLException ex) {
            System.out.println("DocumentDAO HATA(F??ND) :" + ex.getMessage());
        }
        return d;
    }

    @Override
    public boolean update(Document document) {

        try {
            pst = this.getConnection().prepareStatement("update document set name=?,path=?,type=? where id=?");
            pst.setString(1, document.getFileName());
            pst.setString(2, document.getFilePath());
            pst.setString(3, document.getFileType());
            pst.setInt(4, document.getId());

            return pst.executeUpdate() != 0;

        } catch (SQLException ex) {
            System.out.println("DocumentDAO HATA(Update): " + ex.getMessage());
        }
        return false;
    }
}
