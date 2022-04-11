package test;

import dao.KullaniciDAO;
import entity.Kullanici;
import entity.Yetki;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;

public class KullaniciTest {

    KullaniciDAO kullaniciDao = KullaniciDAO.getKullaniciDao();

    @Test
    public void test() {
        Kullanici kullanici = new Kullanici();
        kullanici.setLogin_name("baki");
        kullanici.setSifre("12345");
        kullanici.setAd_soyad("Baki Bülbül");
        kullanici.setCinsiyet("Erkek");
        kullanici.setYas(23);
        kullanici.setCep_telefonu("0555");
        kullanici.setEmail("baki@gmail.com");
        List<Yetki> yetkiler = new ArrayList<>();
        yetkiler.add(new Yetki(4, "Admin"));
        kullanici.setKullaniciYetkileri(yetkiler);
        assertTrue(kullaniciDao.insert(kullanici));
    }

}
