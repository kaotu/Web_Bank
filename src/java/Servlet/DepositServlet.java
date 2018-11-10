/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controller.AccountJpaController;
import Controller.HistoryJpaController;
import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.RollbackFailureException;
import Model.Account;
import Model.History;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

/**
 *
 * @author kao-tu
 */
public class DepositServlet extends HttpServlet {
@Resource
UserTransaction utx;
@PersistenceUnit(unitName = "Web_BankPU")
EntityManagerFactory emf;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account accountSession = (Account) session.getAttribute("account");
        String depositStr = request.getParameter("deposit");
        if(accountSession != null){
            if(depositStr != null && depositStr.length() > 0){
                AccountJpaController accountJpaCtrl = new AccountJpaController(utx, emf);
                HistoryJpaController historyJpaCtrl = new HistoryJpaController(utx, emf);
                Account account = accountJpaCtrl.findAccount(accountSession.getAccountid());
                if(account != null){
                    int deposit = Integer.parseInt(depositStr);
                    if(deposit > 0){
                        boolean checkDepo = account.deposit(deposit);
                        if(checkDepo){
                            History history = new History("deposit",deposit,new Date(),account.getBalance(),account);
                            try {
                                accountJpaCtrl.edit(account);
                                session.setAttribute("message", "Deposit Sucessful!!");
                                historyJpaCtrl.create(history);
                                session.setAttribute("account", account);
                                response.sendRedirect("Account");
                                return;
                            } catch (NonexistentEntityException ex) {
                                Logger.getLogger(DepositServlet.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (RollbackFailureException ex) {
                                Logger.getLogger(DepositServlet.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                Logger.getLogger(DepositServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }else{
                                session.setAttribute("message", "Deposit Unsucessful!!");
                        }
                    }else{
                                session.setAttribute("message", "Deposit Unsucessful!!");
                        }
                }
            }else{
                    session.setAttribute("message", "Deposit Unsucessful!!");
            }   
        }
        getServletContext().getRequestDispatcher("/Account").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
