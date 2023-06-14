import java.util.Random;
import java.util.ArrayList;

public class Estado
{
	private String estado;
	private String nomeEstado;

// ========================================== CONSTRUTOR =============================================
	public Estado()
	{
		Random random = new Random();

		// As pessoas criadas tem 1% de chance de estarem infectadas
		if (random.nextDouble(0, 1.0) <= 0.01)
		{
			this.switchToState("I");
		}
		else
		{
			this.switchToState("S");
		}
	}

// ========================================== MÉTODOS =============================================

	// Muda estado atual para o estado informado
	public void switchToState(String estado)
	{
		switch(estado)
		{
			case "I":
			{
				this.nomeEstado = "INFECTADO";
				this.estado = "I";
				break;
			}

			case "S":
			{
				this.nomeEstado = "SUSCETIVEL";
				this.estado = "S";
				break;
			}

			case "R":
			{
				this.nomeEstado = "RECUPERADO";
				this.estado = "R";
				break;
			}

			default:
			{
				System.out.println("ERRO - ESTADO INFORMADO INVALIDO: " + estado);
				break;
			}
		}
	}

	// Retorna o estado para o qual devemos mudar com base em regras
	public String getNewState()
	{
		Random rng = new Random();
		double chance = rng.nextDouble(0, 1.0);

		// Suscetíveis tem chance de se infectar de acordo com a quantidade de infectados adjacentes
		if (estado.equals("S"))
		{
			// fazer cálculo correto com base na quantidade de infetados na vizinhança!
			// por enquanto, vamos dar 5% de chance de um suscetível se infectar
			if (chance <= 0.05)
			{
				return ("I");
			}
		}

		// Infectados tem 30% de chance de ficarem suscetíveis e 60% de chance de se recuperarem
		if (estado.equals("I"))
		{
			if (chance <= 0.3)
			{
				return ("S");
			}
			else if (chance <= 0.6)
			{
				return ("R");
			}
		}

		// Recuperados tem 10% de chance de voltarem a ser suscetíveis
		if (estado.equals("R"))
		{
			if (chance <= 0.1)
			{
				return ("S");
			}
		}

		// Caso não houve transição, retorna o estado atual
		return (estado);
	}

	// Implementa toString()
	@Override
	public String toString() {
		return estado;
	}
}
