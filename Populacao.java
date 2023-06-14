import java.util.Random;
import java.util.ArrayList;

public class Populacao
{
	// Tamanho da matriz
	private int linhas;
	private int colunas;

	// Status da população
	public int totalInfectados = 0;
	public int totalSuscetiveis = 0;
	public int totalRecuperados = 0;

	// Indivíduos da população
	private Celula[][] geracao;

// ========================================== CONSTRUTOR =============================================

	public Populacao(int linhas, int colunas)
	{
		this.linhas = linhas;
		this.colunas = colunas;
		this.geracao = new Celula[linhas][colunas];

		for (int y = 0; y < linhas; y++)
		{
			for (int x = 0; x < colunas; x++)
			{
				geracao[y][x] = new Celula(y, x);
			}
		}

		// Cria um infectado no meio do array só para garantir
		geracao[(int) linhas / 2][(int) colunas / 2].estado.switchToState("I");

		// Atualiza dados
		this.updateStats();
	}

	// ========================================== MÉTODOS =============================================

	// Atualiza dados sobre a população
	public void updateStats()
	{
		this.totalInfectados = 0;
		this.totalSuscetiveis = 0;
		this.totalRecuperados = 0;

		for (int y = 0; y < linhas; y++)
		{
			for (int x = 0; x < colunas; x++)
			{
				String state = geracao[y][x].estado.toString();

				if (state.equals("I"))
				{
					totalInfectados++;
				}

				if(state.equals("S"))
				{
					totalSuscetiveis++;
				}

				if(state.equals("R"))
				{
					totalRecuperados++;
				}
			}
		}
	}

	// Exibe dados sobre a população
	public void printStats()
	{
		System.out.println("Total de indivíduos: " + (linhas * colunas));
		System.out.println("Total de infectados: " + totalInfectados);
		System.out.println("Total de suscetíveis: " + totalSuscetiveis);
		System.out.println("Total de recuperados: " + totalRecuperados);
	}

	// Retorna um novo mapa com a próxima geração
	public Populacao nextGen()
	{
		Populacao newGen = new Populacao(this.linhas, this.colunas);
		String newState;

		// Percorre as células fazendo a transição de estado
		for (int y = 0; y < linhas; y++)
		{
			for (int x = 0; x < colunas; x++)
			{
				// Estado para o qual devemos mudar com base no atual
				newState = this.geracao[y][x].estado.getNewState();

				// Mudança de estado na população nova
				newGen.geracao[y][x].estado.switchToState(newState);
			}
		}
		// Atualiza dados
		newGen.updateStats();
		return newGen;
	}

	// Retorna um array com as células vizinhas à posição informada
	// IMPLEMENTAÇÃO INCOMPLETA!!
	public void getNeighbors(int linha, int coluna)
	{
		// Usamos um arrayList para  facilitar, depois convertemos para um array normal
		ArrayList<Celula> tempNeighbors = new ArrayList<Celula>();

		// CASOS POSSIVEIS:
		// 1. ESTAMOS NA PRIMEIRA CÉLULA.
		// Vizinhos possíveis: right (y, x+1), down (y+1, x), downRight (x+1, y+1)
		if (linha == 0 && coluna == 0)
		{
			tempNeighbors.add(geracao[0][1]); // right
			tempNeighbors.add(geracao[1][0]); // down
			tempNeighbors.add(geracao[1][1]); // downRight

			// converte ArrayLista pra array normal
			Celula[] neighbors = tempNeighbors.toArray(new Celula[tempNeighbors.size()]);

			// Imprime valores
			for (int i = 0; i < neighbors.length; i++)
			{
				System.out.printf(" %s ", neighbors[i].toString());
			}
			// return neighbors;
		}
	}

	// Implementa toString()
	@Override
	public String toString()
	{
		String populacao = "";
		for (int coluna = 0; coluna < colunas; coluna++) {
			for (int linha = 0; linha < linhas; linha++) {
				populacao += geracao[coluna][linha].toString();
				populacao += " ";
			}
			populacao += "\n";
		}
		return populacao;
	}
}
