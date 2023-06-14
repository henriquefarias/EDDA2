import java.util.Random;
import java.util.ArrayList;

public class Celula
{
	private int linha;
	private int coluna;
	public Estado estado;

	public Celula(int linha, int coluna)
	{
		this.linha = linha;
		this.coluna = coluna;
		this.estado = new Estado();
	}

	@Override
	public String toString()
	{
		return estado.toString();
	}
}
