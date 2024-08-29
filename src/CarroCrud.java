package src;

import java.util.List;

public class CarroCrud {
    private CarroDAO carroDAO;

    public CarroCrud() {
        carroDAO = new CarroDAO();
    }

    public int createCarro(Carro carro) {
        return carroDAO.createCarro(carro);
    }

    public Carro readCarro(int id) {
        return carroDAO.readCarro(id);
    }

    public List<Carro> readAllCarro () {
        return carroDAO.readAllCarro();
    }

    public boolean updateCarro (Carro carro) {
        return carroDAO.updateCarro(carro);
    }

    public boolean deleteCarro (int id) {
        return carroDAO.deleteCarro(id);
    }

}
