package modele;

public class Impression {
    private int idImpression;
    private int quantite;
    private String format;
    private String typePapier;

    public Impression(int idImpression, int quantite, String format, String typePapier) {
        this.idImpression = idImpression;
        this.quantite = quantite;
        this.format = format;
        this.typePapier = typePapier;
    }

    public int getIdImpression() { return idImpression; }
    public int getQuantite() { return quantite; }
    public String getFormat() { return format; }
    public String getTypePapier() { return typePapier; }

    public void setQuantite(int quantite) { this.quantite = quantite; }
    public void setFormat(String format) { this.format = format; }
    public void setTypePapier(String typePapier) { this.typePapier = typePapier; }

    @Override
    public String toString() {
        return "[ID: " + idImpression + ", Quantité: " + quantite + ", Format: " + format + ", Papier: " + typePapier + "]";
    }
}
