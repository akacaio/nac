package br.com.fiap.repository;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import br.com.fiap.exception.ResponseException;
import br.com.fiap.to.Venda;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class VendaRepository {
	
	private Client client = Client.create();
	
	private static final String URL = "http://localhost:8080/Exercicio-01/rest/venda/";
	
	public void remover(int codigo) throws ResponseException {
		
		WebResource resource = client.resource(URL).path(String.valueOf(codigo));
		
		ClientResponse resp = resource.delete(ClientResponse.class);
		
		if (resp.getStatus() != 204) {
			throw new ResponseException();
		}
		
	}
	
	public void atualizar(Venda venda) throws ResponseException {
		
		WebResource resource = client.resource(URL).path(Integer.toString(venda.getId()));
		
		ClientResponse resp = resource.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, venda);
		
		if (resp.getStatus() != 200) {
			throw new ResponseException();
		}
		
	}
	
	public void cadastrar(Venda venda) throws ResponseException {
		
		WebResource resource = client.resource(URL);
		
		ClientResponse resp = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, venda);
		
		if (resp.getStatus() != 201) {
			throw new ResponseException();
		}
		
	}
	
	public Venda pesquisa(int codigo) throws ResponseException {
		
		WebResource resource = client.resource(URL).path(Integer.toString(codigo));
		
		ClientResponse resp = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		
		if (resp.getStatus() != 200) {
			throw new ResponseException();
		}
		
		return resp.getEntity(Venda.class);
	}

	public List<Venda> listar() throws ResponseException{
		
		WebResource resource = client.resource(URL);
		
		ClientResponse resp = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		
		if (resp.getStatus() != 200) {
			throw new ResponseException();
		}
		
		Venda[] vetor = resp.getEntity(Venda[].class);		
		return Arrays.asList(vetor); 
	}
	
}
