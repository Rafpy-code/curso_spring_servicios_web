package init.service.implementations;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import init.model.Coche;
import init.service.interfaces.CocheServiceInterface;

@Service
public class CocheServiceImpl implements CocheServiceInterface {

	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static List<Coche> coches = new ArrayList<>(cargarCoches());

	static List<Coche> cargarCoches() {
		try {
			return List.of(new Coche("abc123", "kia", "rio", 247560, 2350.50, sdf.parse("01/01/2019")),
					new Coche("abc456", "kia", "sorento", 47560, 350.50, sdf.parse("02/01/2020")),
					new Coche("abc789", "bmw", "series 5", 22560, 1350.50, sdf.parse("03/01/2021")),
					new Coche("abc000", "alfa romeo", "stelvio", 7560, 2250.50, sdf.parse("04/01/2022")),
					new Coche("abc111", "audi", "q5", 924560, 7350.50, sdf.parse("05/01/2023")),
					new Coche("abc666", "ford", "explorer", 560, 30350.50, sdf.parse("06/01/2024")));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Coche findByMatricula(String matricula) {
		return coches.stream().filter(c -> c.getMatricula().equalsIgnoreCase(matricula)).findFirst().orElse(null);
	}

	@Override
	public void save(Coche coche) {
		if (findByMatricula(coche.getMatricula()) == null) {
			coches.add(coche);
		}
	}

	@Override
	public List<Coche> findAll() {
		return coches.stream().toList();
		// return coches
	}

	@Override
	public Coche delete(String matricula) {
		Coche coche = findByMatricula(matricula);
		if(coche!=null) {
			coches.removeIf(c -> c.getMatricula().equals(matricula));
		}
		return coche;
	}

	@Override
	public List<Coche> findByMarca(String marca) {
		return coches.stream().filter(c -> c.getMarca().equals(marca)).toList();
	}

	@Override
	public List<Coche> findByPrecioMax(double precioMax) {
		List<Coche> encontrados = new ArrayList<Coche>();
		//return coches.stream().filter(c->c.getPrecio()<=precioMax).toList();
		for (Coche c : coches) {
			if (c.getPrecio() <= precioMax) {
				encontrados.add(c);
			}
		}
		return encontrados;
	}

	@Override
	public void update(Coche coche) {
		Coche c = findByMatricula(coche.getMatricula());
		if (c != null) {
			c.setMarca(coche.getMarca());
			c.setModelo(coche.getModelo());
			c.setKilometros(coche.getKilometros());
			c.setPrecio(coche.getPrecio());
			c.setFechaFabricacion(coche.getFechaFabricacion());
		}
	}

}
