package org.factoriaf5.computershop.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private static Long id;
        private String name;
        private String owner;
        private String taxid;
       
          // Constructor
          public Shop(Long id, String name, String owner, String taxid) {
            Shop.id = id;
            this.name = name;
            this.owner = owner;
            this.taxid = taxid;
        }
    
        // Métodos getter y setter para los atributos
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public String getOwner() {
            return owner;
        }
    
        public void setOwner(String owner) {
            this.owner = owner;
        }
    
        public String getTaxid() {
            return taxid;
        }
    
        public void setTaxid(String taxid) {
            this.taxid = taxid;
        }
    
        // Método para mostrar la información de la tienda
        @Override
        public String toString() {
            return "Tienda [Nombre=" + name + ", Propietario=" + owner + ", Identificador Tributario=" + taxid + "]";
        }
    
        // Método main para probar la clase Tienda
        public static void main(String[] args) {
            new Shop(id, "TechStore", "Juan Pérez", "RUC-123456789");

        
    }
}


