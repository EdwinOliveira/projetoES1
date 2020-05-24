# Projeto final ES1 2019/2020
## Problema
A loja virtual Ablazon recebe encomendas de livros de bibliotecas de vários países. As encomendas obedecem sempre ao seguinte workflow

- A encomenda é submetida na plataforma online da loja. Para além dos dados dos livros e quantidades, pode ser também incluído o código de um voucher;

- Depois de validar uma encomenda, a aplicação envia uma confirmação da receção da mesma para o e-mail do responsável da biblioteca. Esta confirmação inclui a data prevista de entrega e o link para fazer o acompanhamento da mesma;

- A mudança de estado da encomenda (ex: processada->enviada) dá origem ao envio de um e-mail para o responsável pela biblioteca;

- Os funcionários do loja podem enviar parte da encomenda para os clientes se verificarem que alguns items podem sofrer atrasos significativos;

- A aplicação fornece uma API à transportadora para registar a entrega dos livros ao cliente. Sempre que a transportadora comunicar uma entrega, a aplicação deverá enviar um e-mail com um link para os clientes darem feedback do serviço.
