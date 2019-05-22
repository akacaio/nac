	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return "Descri��o: " + descricao + "\nValor: " + valor + "\nData: " + 
				sdf.format(data.getTime());
	}
