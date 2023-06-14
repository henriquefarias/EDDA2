import java.util.Random;
import java.util.ArrayList;

public class Test
{
	public static void main(String[] args)
	{
		// Total de gerações a serem calculadas
		int maxGenerations = 100;

		// Tamanho da matriz
		int mapSize = 21;

		// Array de gerações
		// Isso não é necessário, tecnicamente precisamos apenas da geração atual e da próxima.
		// Porém o array facilita ler os valores ao longo do tempo.
		Populacao[] generations = new Populacao[maxGenerations];

		// Primeira geração
		generations[0] = new Populacao(mapSize, mapSize);
		generations[0].printStats();

		// Gerações subsequentes
		for (int i = 1; i < maxGenerations; i++)
		{
			generations[i] = generations[i - 1].nextGen();
			System.out.println(" GERAÇÃO " + i);
			generations[i].printStats();
		}
	}
}
