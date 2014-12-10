package oracle.entities;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
  @NamedQuery(name = "Photos.findAll", query = "select o from Photos o")
})
public class Photos implements Serializable {
    @Column(nullable = false)
    private byte[] image;
    @Id
    @Column(nullable = false)
    private Long imageid;
    @Column(nullable = false, length = 5)
    private String type;
    @OneToMany(mappedBy = "photos")
    private List<Fotos> fotosList;
    @OneToMany(mappedBy = "photos")
    private List<Inmobiliaria> inmobiliariaList;
    @OneToMany(mappedBy = "photos")
    private List<Empresacorredora> empresacorredoraList;

    public Photos() {
    }

    public Photos(Long imageid, String type) {
        this.imageid = imageid;
        this.type = type;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Long getImageid() {
        return imageid;
    }

    public void setImageid(Long imageid) {
        this.imageid = imageid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Fotos> getFotosList() {
        return fotosList;
    }

    public void setFotosList(List<Fotos> fotosList) {
        this.fotosList = fotosList;
    }

    public Fotos addFotos(Fotos fotos) {
        getFotosList().add(fotos);
        fotos.setPhotos(this);
        return fotos;
    }

    public Fotos removeFotos(Fotos fotos) {
        getFotosList().remove(fotos);
        fotos.setPhotos(null);
        return fotos;
    }

    public List<Inmobiliaria> getInmobiliariaList() {
        return inmobiliariaList;
    }

    public void setInmobiliariaList(List<Inmobiliaria> inmobiliariaList) {
        this.inmobiliariaList = inmobiliariaList;
    }

    public Inmobiliaria addInmobiliaria(Inmobiliaria inmobiliaria) {
        getInmobiliariaList().add(inmobiliaria);
        inmobiliaria.setPhotos(this);
        return inmobiliaria;
    }

    public Inmobiliaria removeInmobiliaria(Inmobiliaria inmobiliaria) {
        getInmobiliariaList().remove(inmobiliaria);
        inmobiliaria.setPhotos(null);
        return inmobiliaria;
    }

    public List<Empresacorredora> getEmpresacorredoraList() {
        return empresacorredoraList;
    }

    public void setEmpresacorredoraList(List<Empresacorredora> empresacorredoraList) {
        this.empresacorredoraList = empresacorredoraList;
    }

    public Empresacorredora addEmpresacorredora(Empresacorredora empresacorredora) {
        getEmpresacorredoraList().add(empresacorredora);
        empresacorredora.setPhotos(this);
        return empresacorredora;
    }

    public Empresacorredora removeEmpresacorredora(Empresacorredora empresacorredora) {
        getEmpresacorredoraList().remove(empresacorredora);
        empresacorredora.setPhotos(null);
        return empresacorredora;
    }
}
