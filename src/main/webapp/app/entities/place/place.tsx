import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { openFile, byteSize, Translate, TextFormat, getPaginationState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './place.reducer';

export const Place = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const placeList = useAppSelector(state => state.place.entities);
  const loading = useAppSelector(state => state.place.loading);
  const totalItems = useAppSelector(state => state.place.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(pageLocation.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [pageLocation.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = paginationState.sort;
    const order = paginationState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="place-heading" data-cy="PlaceHeading">
        <Translate contentKey="jhipsterSampleApplication1App.place.home.title">Places</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="jhipsterSampleApplication1App.place.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/place/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhipsterSampleApplication1App.place.home.createLabel">Create new Place</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {placeList && placeList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="jhipsterSampleApplication1App.place.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('code')}>
                  <Translate contentKey="jhipsterSampleApplication1App.place.code">Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('code')} />
                </th>
                <th className="hand" onClick={sort('enabled')}>
                  <Translate contentKey="jhipsterSampleApplication1App.place.enabled">Enabled</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('enabled')} />
                </th>
                <th className="hand" onClick={sort('icon')}>
                  <Translate contentKey="jhipsterSampleApplication1App.place.icon">Icon</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('icon')} />
                </th>
                <th className="hand" onClick={sort('destinationSight')}>
                  <Translate contentKey="jhipsterSampleApplication1App.place.destinationSight">Destination Sight</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('destinationSight')} />
                </th>
                <th className="hand" onClick={sort('longitude')}>
                  <Translate contentKey="jhipsterSampleApplication1App.place.longitude">Longitude</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('longitude')} />
                </th>
                <th className="hand" onClick={sort('latitude')}>
                  <Translate contentKey="jhipsterSampleApplication1App.place.latitude">Latitude</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('latitude')} />
                </th>
                <th className="hand" onClick={sort('createdDate')}>
                  <Translate contentKey="jhipsterSampleApplication1App.place.createdDate">Created Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdDate')} />
                </th>
                <th className="hand" onClick={sort('defaultImage')}>
                  <Translate contentKey="jhipsterSampleApplication1App.place.defaultImage">Default Image</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('defaultImage')} />
                </th>
                <th className="hand" onClick={sort('defaultImageData')}>
                  <Translate contentKey="jhipsterSampleApplication1App.place.defaultImageData">Default Image Data</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('defaultImageData')} />
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplication1App.place.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplication1App.place.destination">Destination</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {placeList.map((place, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/place/${place.id}`} color="link" size="sm">
                      {place.id}
                    </Button>
                  </td>
                  <td>{place.code}</td>
                  <td>{place.enabled ? 'true' : 'false'}</td>
                  <td>{place.icon}</td>
                  <td>{place.destinationSight ? 'true' : 'false'}</td>
                  <td>{place.longitude}</td>
                  <td>{place.latitude}</td>
                  <td>{place.createdDate ? <TextFormat type="date" value={place.createdDate} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{place.defaultImage}</td>
                  <td>
                    {place.defaultImageData ? (
                      <div>
                        {place.defaultImageDataContentType ? (
                          <a onClick={openFile(place.defaultImageDataContentType, place.defaultImageData)}>
                            <img
                              src={`data:${place.defaultImageDataContentType};base64,${place.defaultImageData}`}
                              style={{ maxHeight: '30px' }}
                            />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {place.defaultImageDataContentType}, {byteSize(place.defaultImageData)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{place.createdBy ? place.createdBy.login : ''}</td>
                  <td>{place.destination ? <Link to={`/destination/${place.destination.id}`}>{place.destination.code}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/place/${place.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/place/${place.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() =>
                          (window.location.href = `/place/${place.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
                        }
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="jhipsterSampleApplication1App.place.home.notFound">No Places found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={placeList && placeList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default Place;
