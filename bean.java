package br.com.fiap.bean;

import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.exception.ResponseException;
import br.com.fiap.repository.VendaRepository;
import br.com.fiap.to.Venda;

@ManagedBean
@RequestScoped
public class VendaBean  {

	private VendaRepository reposistory;
	
	private Venda venda;
	
	@PostConstruct
	public void init() {
		reposistory = new VendaRepository();
		venda = new Venda();
		venda.setData(Calendar.getInstance());
	}
	
	public String salvar() {
		try {
			if (venda.getId() == 0) {
				reposistory.cadastrar(venda);
				adicionarMensagem("Cadastrado!");
				return "venda?faces-redirect=true";
			}else {
				reposistory.atualizar(venda);
				adicionarMensagem("Atualizado!");
				return "lista-vendas?faces-redirect=true";
			}			
		} catch (ResponseException e) {
			e.printStackTrace();
			adicionarMensagem("Erro..");
			return "venda";
		}
	}
	
	public String excluir(int codigo) {
		try {
			reposistory.remover(codigo);
			adicionarMensagem("Removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "lista-vendas?faces-redirect=true";
	}
	
	public List<Venda> listar(){
		try {
			return reposistory.listar();
		} catch (ResponseException e) {
			e.printStackTrace();
			adicionarMensagem("N�o foi poss�vel listar, tente mais tarde.");
			return null;
		}
	}
	
	
	private void adicionarMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
}
